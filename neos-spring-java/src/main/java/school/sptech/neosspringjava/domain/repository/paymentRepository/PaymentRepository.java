package school.sptech.neosspringjava.domain.repository.paymentRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

//    List<Payment> findAllByEstablishmentOrderByDateTimeDesc(Establishment establishment);
    

    @Query("SELECT p FROM Payment p " +
           "JOIN p.schedule s " +
           "JOIN s.employee e " +
           "JOIN p.market m " +
           "JOIN m.product pr " +
           "WHERE (p.datePayment BETWEEN :startDate AND :endDate AND e.establishment.id = :establishmentId) " +
           "OR (p.datePayment BETWEEN :startDate AND :endDate AND pr.establishment.id = :establishmentId)")
    List<Payment> findPaymentsByDateRangeAndEstablishment(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("establishmentId") Integer establishmentId
    );

    @Query("SELECT p " +
       "FROM Payment p " +
       "JOIN p.market m " +
       "JOIN p.status s " +
       "JOIN m.product pr " +
       "WHERE(p.datePayment BETWEEN :startDate AND :endDate AND pr.establishment.id = :establishmentId)"+
       "ORDER BY p.datePayment DESC")
    List<Payment> findMarketQuantityAndStatusNameByDateRangeAndEstablishment(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("establishmentId") Integer establishmentId
    );
}
