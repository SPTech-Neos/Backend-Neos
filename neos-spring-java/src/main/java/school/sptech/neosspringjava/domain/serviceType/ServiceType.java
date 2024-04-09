package school.sptech.neosspringjava.domain.serviceType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ServiceType {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome do tipo de serviço é obrigatório")
    @NotEmpty(message = "Nome do tipo de serviço é obrigatório")
    private String name;
    @Getter
    @Setter
    private Integer fkServiceCategory;


}
