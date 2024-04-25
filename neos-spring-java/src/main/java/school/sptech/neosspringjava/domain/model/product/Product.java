package school.sptech.neosspringjava.domain.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome do produto é obrigatório")
    @NotEmpty(message = "Nome do produto é obrigatório")
    private String name;
    @Getter
    @Setter
    private String brand;
    @Getter
    @Setter
    @NotBlank
    private Integer fkProductType;
    @Getter
    @Setter
    @NotBlank
    private Integer fkEstablishment;
    
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", fkProductType=" + fkProductType
                + ", fkEstablishment=" + fkEstablishment + "]";
    }

    


}
