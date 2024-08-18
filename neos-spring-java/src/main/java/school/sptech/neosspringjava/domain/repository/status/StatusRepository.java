package school.sptech.neosspringjava.domain.repository.status;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findByName(String name);
}
