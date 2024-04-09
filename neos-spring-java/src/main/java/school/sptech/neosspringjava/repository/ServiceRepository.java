package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.service.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
