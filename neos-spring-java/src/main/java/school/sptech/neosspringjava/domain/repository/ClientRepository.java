package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import school.sptech.neosspringjava.domain.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);
    //UserDetails findByEmail(String email);
    
    public Client findByEmailAndPassword(String email, String password);

    public Boolean existsByEmail(String email);

  

}