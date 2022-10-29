package api;

import dto.UserRequestDTO;
import dto.UserResponseDTO;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<UserResponseDTO> create (UserRequestDTO userRequestDTO);
}
