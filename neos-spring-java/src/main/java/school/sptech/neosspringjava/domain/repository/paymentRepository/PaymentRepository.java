package school.sptech.neosspringjava.domain.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
