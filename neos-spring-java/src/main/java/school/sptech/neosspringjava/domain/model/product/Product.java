package school.sptech.neosspringjava.domain.model.product;

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
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.productType.ProductType;

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
    @NotBlank(message = "Nome do produto é obrigatório")
    @NotEmpty(message = "Nome do produto é obrigatório")
    private String name;
    private String brand;
    private String imgUrl;
    private Double value;
    @ManyToOne
    @JoinColumn(name = "type_fk")
    private ProductType type;
    @ManyToOne
    @JoinColumn(name = "establishment_fk")
    private Establishment establishment;
    
    
   
   

    


}
