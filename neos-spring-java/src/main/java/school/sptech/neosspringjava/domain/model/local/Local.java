package school.sptech.neosspringjava.domain.model.local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.address.Address;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocal;

    @NotNull(message = "CEP é obrigatório")
    private String cep;

    @NotNull(message = "Número é obrigatório")
    private int number;

    private int floor;
    private String bloc;
    private String complement;

    @NotNull(message = "FkEndereco é obrigatório")
    @ManyToOne
    private Address address;
}
