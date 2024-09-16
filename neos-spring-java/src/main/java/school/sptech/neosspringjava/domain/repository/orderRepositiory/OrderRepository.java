package school.sptech.neosspringjava.domain.repository.orderRepositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.neosspringjava.domain.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
