package school.sptech.neosspringjava.domain.repository.paymentRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // Encontrar todos os pagamentos associados a um Employee através do Scheduling e ordenados pela data
    List<Payment> findAllByScheduleEmployeeIdOrderByDatePaymentDesc(Integer employeeId);

    // Encontrar todos os pagamentos associados a um Order e um Employee (via Scheduling) e ordenados pela data
    List<Payment> findAllByOrderAndScheduleEmployeeIdOrderByDatePaymentDesc(Order order, Integer employeeId);

    // Encontrar todos os pagamentos associados a um Status e um Employee (via Scheduling) e ordenados pela data
    List<Payment> findAllByStatusAndScheduleEmployeeIdOrderByDatePaymentDesc(Status status, Integer employeeId);

    // Encontrar todos os pagamentos dentro de um intervalo de datas e associados a um Employee (via Scheduling)
    List<Payment> findByDatePaymentBetweenAndScheduleEmployeeIdOrderByDatePaymentDesc(LocalDateTime start, LocalDateTime end, Integer employeeId);

}
