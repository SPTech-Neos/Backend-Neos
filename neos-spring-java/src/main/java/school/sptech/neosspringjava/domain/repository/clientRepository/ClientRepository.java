package school.sptech.neosspringjava.domain.repository.clientRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.client.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    public Client findByEmailAndPassword(String email, String password);

    public Boolean existsByEmail(String email);

  

}