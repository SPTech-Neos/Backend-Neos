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
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;

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
    @Column(name = "date_payment")
    private LocalDateTime dateTime;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_fk")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "establishment_fk")
    private Establishment establishment;


}
