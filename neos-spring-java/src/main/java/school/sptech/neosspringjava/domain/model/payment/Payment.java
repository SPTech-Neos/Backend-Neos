package school.sptech.neosspringjava.domain.model.payment;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.status.Status;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @Column(name = "datePayment")
    private LocalDateTime datePayment;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "fkSchedule")
    private Scheduling schedule;
    
    @ManyToOne
    @JoinColumn(name = "fkOrder")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fkStatus")
    private Status status;


}
