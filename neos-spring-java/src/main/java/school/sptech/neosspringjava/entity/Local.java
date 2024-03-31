package school.sptech.neosspringjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "local")
public class Local {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocal;

    @Getter
    @Setter
    @NotNull(message = "Número é obrigatório")
    private int numero;
    @Getter
    @Setter
    private int andar;
    @Getter
    @Setter
    private String bloco;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    @NotNull(message = "FkEndereco é obrigatório")
    private int fkEndereco;

}
