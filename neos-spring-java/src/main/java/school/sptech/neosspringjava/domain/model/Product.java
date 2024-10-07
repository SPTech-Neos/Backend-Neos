package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.ProductType;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    private String name;

    private String brand;

    private String imgUrl;

    private Double price;
    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "fk_product_type")
    private ProductType type;
    @ManyToOne
    @JoinColumn(name = "fk_establishment")
    private Establishment establishment;

}
