package school.sptech.neosspringjava.domain.repository.serviceRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.service.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

    // List<Service> findAllByEstablishment(Establishment establishmentId);
}
