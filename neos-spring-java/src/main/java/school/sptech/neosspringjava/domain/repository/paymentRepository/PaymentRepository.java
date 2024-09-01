package school.sptech.neosspringjava.domain.repository.paymentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

//    List<Payment> findAllByEstablishmentOrderByDateTimeDesc(Establishment establishment);

    List<Payment> findAllByEstablishmentAndDateTimeGreaterThanEqualOrderByDateTimeDesc(Establishment establishment, LocalDateTime start);

    List<Payment> findByClientId(Integer clientId);

    List<Payment> findByEstablishmentId(Integer clientId);

    List<Payment> findByIdAndDateTimeBetweenOrderByDateTimeDesc(Integer establishment, LocalDateTime start, LocalDateTime end);
    

}
