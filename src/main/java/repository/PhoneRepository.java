package repository;

import model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {

    Optional<Phone> findByUuid (Long uuid);
}
