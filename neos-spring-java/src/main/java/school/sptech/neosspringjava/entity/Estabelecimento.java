package school.sptech.neosspringjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstabelecimento;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @Getter
    @Setter
    @NotNull(message = "FkEmpresa é obrigatório")
    private int fkEmpresa;
    @Getter
    @Setter
    @NotNull(message = "FkLocal é obrigatório")
    private int fkLocal;
}
