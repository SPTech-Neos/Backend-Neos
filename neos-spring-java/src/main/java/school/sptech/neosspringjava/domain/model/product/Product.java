package school.sptech.neosspringjava.domain.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome do produto é obrigatório")
    @NotEmpty(message = "Nome do produto é obrigatório")
    private String name;
    private String brand;
    @NotBlank
    private Integer fkProductType;
    @NotBlank
    private Integer fkEstablishment;
    
   
   

    


}
