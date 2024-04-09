package school.sptech.neosspringjava.domain.serviceCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_category")
public class ServiceCategory{

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
}
