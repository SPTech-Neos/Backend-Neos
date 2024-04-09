package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.serviceType.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,Integer > {

}
