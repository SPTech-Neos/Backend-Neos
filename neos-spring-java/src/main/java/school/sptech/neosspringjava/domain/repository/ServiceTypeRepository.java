package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,Integer > {

}
