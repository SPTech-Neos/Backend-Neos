package school.sptech.neosspringjava.domain.model.filter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Filter {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Double preco;
    @Getter
    @Setter
    private Integer fkEstablishment;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkService;
    @Override
    public String toString() {
        return "Filter [id=" + id + ", preco=" + preco + ", fkEstablishment=" + fkEstablishment + ", fkService="
                + fkService + "]";
    }
    
}
