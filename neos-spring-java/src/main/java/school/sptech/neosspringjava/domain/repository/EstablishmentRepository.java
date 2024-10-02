package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Service;
import school.sptech.neosspringjava.domain.model.Status;

import java.util.List;
import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer>{
    List<Establishment> findAllByStatus(Status status);

}
