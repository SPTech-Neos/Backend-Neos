package school.sptech.neosspringjava.domain.repository.orderRepositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // List<Order> findByEstablishmentAndStatus(Integer id, Integer status);

    // List<Order> findByEmployeeAndStatus(Integer id, Integer status);
}
