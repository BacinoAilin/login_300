package service;


import api.IUserService;
import dto.UserRequestDTO;
import dto.UserResponseDTO;
import exception.MailException;
import exception.PasswordException;
import exception.UserException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import repository.UserRepository;
import util.JwtUtil;

import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Value("${user.email.regex}")
    private String mailRegex;

    @Value("${user.password.regex}")
    private String passwordRegex;


    private boolean regexPattern(String regexRequest, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(regexRequest)
                .matches();
    }
    @Override
    public Mono<UserResponseDTO> create(UserRequestDTO userRequestDTO) {


        if (userRepository.findByEmail(userRequestDTO.getMail()).isPresent()) {
            throw new UserException();
        }
        if (!regexPattern(userRequestDTO.getMail(), mailRegex)) {
            throw new MailException();
        }
        if (!regexPattern(userRequestDTO.getPassword(), passwordRegex)) {
            throw new PasswordException();
        }

        User user = userRequestDTO.build();
        user.setToken(new JwtUtil().generateToken(user));

        return Mono.just(user)
                .flatMap(user1 -> Mono.just(userRepository.save(user1)))
                .map(UserResponseDTO::new);
    }
}
