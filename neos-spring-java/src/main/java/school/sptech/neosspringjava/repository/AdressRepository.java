package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.entity.Address;

public interface EnderecoRepository extends JpaRepository<Address, Integer>{

}
