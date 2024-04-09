package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.address.Address;

public interface AdressRepository extends JpaRepository<Address, Integer>{

}
