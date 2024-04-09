package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.client.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}