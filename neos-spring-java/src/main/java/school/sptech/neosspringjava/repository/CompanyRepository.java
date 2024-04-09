package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
