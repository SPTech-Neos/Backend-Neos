package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Integer id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "fk_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;

}
