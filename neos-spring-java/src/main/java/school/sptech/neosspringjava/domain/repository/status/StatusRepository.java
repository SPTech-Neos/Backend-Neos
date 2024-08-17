package school.sptech.neosspringjava.domain.repository.status;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByName(String name);
}
