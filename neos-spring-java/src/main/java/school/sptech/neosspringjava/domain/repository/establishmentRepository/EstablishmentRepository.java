package school.sptech.neosspringjava.domain.repository.establishmentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.util.List;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer>{
    List<Establishment> findAllByStatus(Status status);
}
