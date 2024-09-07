package school.sptech.neosspringjava.domain.repository.orderRepositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

   

    @Query("SELECT o FROM Order o WHERE o.employee.establishment.id = :establishmentId AND o.status.id = :statusId")
    List<Order> findByEstablishmentAndStatus(@Param("establishmentId") Integer establishmentId, @Param("statusId") Integer statusId);



    @Query("SELECT o FROM Order o WHERE o.client.id = :clientId AND o.status.id = :statusId")
    List<Order> findByClientAndStatus(@Param("clientId") Integer clientId, @Param("statusId") Integer statusId);

    @Query("SELECT o FROM Order o WHERE o.employee.id = :employeeId AND o.status.id = :statusId")
    List<Order> findByEmployeeAndStatus(@Param("employeeId") Integer employeeId, @Param("statusId") Integer statusId);
    
}
