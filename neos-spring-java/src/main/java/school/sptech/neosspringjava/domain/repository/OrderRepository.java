package school.sptech.neosspringjava.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.neosspringjava.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // List<Order> findByEstablishmentAndStatus(Integer id, Integer status);

    // List<Order> findByEmployeeAndStatus(Integer id, Integer status);

    @Query("SELECT o FROM Order o WHERE o.client.id = :clientId AND o.status.id = :statusId")
    List<Order> findOrdersByClientIdAndStatusId(Integer clientId, Integer statusId);
}
