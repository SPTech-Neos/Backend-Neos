package school.sptech.neosspringjava.domain.repository.localRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.model.local.Local;

public interface LocalRepository extends JpaRepository<Local, Integer>{

    Local findByAddress(Address address);
}
