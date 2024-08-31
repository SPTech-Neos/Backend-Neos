package school.sptech.neosspringjava.domain.repository.paymentRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

//    List<Payment> findAllByEstablishmentOrderByDateTimeDesc(Establishment establishment);

}
