package school.sptech.neosspringjava.domain.repository.filterRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;


public interface FilterRepository extends JpaRepository <Filter, Integer>{
    List<Filter> findAllByEstablishment(Establishment establishment);


}
