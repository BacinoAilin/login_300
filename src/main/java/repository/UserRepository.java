package repository;


import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, String> {

    Optional<User> findByUuId (UUID userId);

    Optional<User> findByEmail (String email);
}
