package school.sptech.neosspringjava.domain.model.local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Local {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocal;

    @Getter
    @Setter
    @NotNull(message = "Número é obrigatório")
    private int number;
    @Getter
    @Setter
    private int floor;
    @Getter
    @Setter
    private String bloc;
    @Getter
    @Setter
    private String complement;
    @Getter
    @Setter
    @NotNull(message = "FkEndereco é obrigatório")
    private int fkAddress;

}
