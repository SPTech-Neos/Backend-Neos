package school.sptech.neosspringjava.domain.productType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ProductType {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome do tipo do produto é obrigatório")
    @NotEmpty(message = "Nome do tipo do produto é obrigatório")
    private String name;
    @Getter
    @Setter
    private String specification;
    
    @Override
    public String toString() {
        return "ProductType [id=" + id + ", name=" + name + ", specification=" + specification + "]";
    }
}
