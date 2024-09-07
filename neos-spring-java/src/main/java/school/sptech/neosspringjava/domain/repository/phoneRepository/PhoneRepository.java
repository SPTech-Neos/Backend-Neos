package school.sptech.neosspringjava.domain.repository.phoneRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.phone.Phone;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    Optional<Phone> findByAreaCodeAndNumber(String areaCode, String number);
}
