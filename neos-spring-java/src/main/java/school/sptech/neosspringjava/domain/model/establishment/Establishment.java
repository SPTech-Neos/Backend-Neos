package school.sptech.neosspringjava.domain.model.establishment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.local.Local;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Table(name = "Establishment")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstablishment;
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @ManyToOne
    private Local local;
    
}
