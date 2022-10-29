package controller;


import api.IUserService;
import controller.restRoute.RestRoute;
import dto.UserRequestDTO;
import dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    IUserService userService;

    @PostMapping(value = RestRoute.USER.USER_SAVE, produces = "application/json")
    @Operation(summary = "Creates an user",
            description = "Creates and registrates an user, given the parameter fields.",
            requestBody = @RequestBody(
                    description = "Request format",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            })
    public Mono<ResponseEntity<UserResponseDTO>> create(@RequestBody final UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO)
                .flatMap(dto -> Mono.just(ResponseEntity
                        .ok()
                        .body(dto)));
    }

}
