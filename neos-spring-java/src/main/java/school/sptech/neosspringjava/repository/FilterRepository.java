package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.filter.Filter;


public interface FilterRepository extends JpaRepository <Filter, Integer>{

}
