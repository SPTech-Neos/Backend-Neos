package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
