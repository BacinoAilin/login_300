package service;

import dto.PhoneRequestDTO;
import dto.UserRequestDTO;
import model.Phone;
import model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.test.StepVerifier;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@SpringJUnitConfig(UserServiceTest.UserServiceTestConfiguration.class)
@TestPropertySource(properties = { "user.password.regex=^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" })
@TestPropertySource(properties = { "user.email.regex=^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" })
public class UserServiceTest {


    @Autowired
    UserService userService;

    final String uuidString = "123e4567-e89b-12d3-a456-426614174000";
    final UUID uuid =  UUID.fromString(uuidString);
    @Test
    void createTest() {


    }

    public static class UserServiceTestConfiguration {

        @Bean
        public UserService userService() {return new UserService();}

        @Bean
        public UserRepository userRepository() {
            final UserRepository userRepository = Mockito.mock(UserRepository.class);

            final String uuidString = "123e4567-e89b-12d3-a456-426614174000";
            final UUID uuid =  UUID.fromString(uuidString);

            final Phone phone = Phone.builder()
                    .phoneId(1L)
                    .number(1234L)
                    .cityCode("7600")
                    .countryCode("767")
                    .build();

            final User user = User.builder()
                    .userId(uuid)
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build();

            final User userSave = User.builder()
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build();



            Mockito.when(userRepository.findByUuId(uuid)).thenReturn(Optional.of(user));
            Mockito.when(userRepository.save(userSave)).thenReturn(User.builder()
                    .userId(uuid)
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build());

            return userRepository;
        }
    }
}