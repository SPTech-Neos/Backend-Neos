package school.sptech.neosspringjava.domain.repository.establishmentRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer>{
}
