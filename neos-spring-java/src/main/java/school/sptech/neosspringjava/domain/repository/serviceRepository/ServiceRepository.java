package school.sptech.neosspringjava.domain.repository.serviceRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.status.Status;

public interface ServiceRepository extends JpaRepository<Service, Integer>{


}
