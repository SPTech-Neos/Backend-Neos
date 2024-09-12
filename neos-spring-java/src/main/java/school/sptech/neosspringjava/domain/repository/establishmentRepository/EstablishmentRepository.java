package school.sptech.neosspringjava.domain.repository.establishmentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.status.Status;

import school.sptech.neosspringjava.domain.model.service.Service;

import java.util.List;
import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer>{
    List<Establishment> findAllByStatus(Status status);

}
