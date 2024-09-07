package school.sptech.neosspringjava.domain.model.market;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.model.product.Product;

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
    @JoinColumn(name = "fkProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fkOrder")
    private Order order;

}
