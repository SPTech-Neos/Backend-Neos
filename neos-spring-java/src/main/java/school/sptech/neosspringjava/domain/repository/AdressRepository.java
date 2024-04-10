package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.client.address.Address;

public interface AdressRepository extends JpaRepository<Address, Integer>{

}
