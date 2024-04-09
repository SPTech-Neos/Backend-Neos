package school.sptech.neosspringjava.domain.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Service {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "É necessario haver uma especificação no serviço")
    @NotEmpty(message = "É necessario haver uma especificação no serviço")
    private String specification;
    @Getter
    @Setter
    private Integer fkServiceType;
    @Override
    public String toString() {
        return "Service [id=" + id + ", specification=" + specification + ", fkServiceType=" + fkServiceType + "]";
    }
    

}
