package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.entity.Company;

public interface EmpresaRepository extends JpaRepository<Company, Integer>{

}
