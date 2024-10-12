package school.sptech.neosspringjava.domain.model;

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
import school.sptech.neosspringjava.domain.model.Product;

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

    private LocalDateTime datePayment;

    @ManyToOne
    @JoinColumn(name = "fk_market")
    private Market market;

    @ManyToOne
    @JoinColumn(name = "fk_schedule")
    private Scheduling schedule;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;


}
