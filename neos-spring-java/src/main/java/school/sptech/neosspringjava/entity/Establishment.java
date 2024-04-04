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
@Table(name = "Establishment")
public class Establishment {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstablishment;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @Getter
    @Setter
    @NotNull(message = "FkEmpresa é obrigatório")
    private int fkEstablishment;
    @Getter
    @Setter
    @NotNull(message = "FkLocal é obrigatório")
    private int fkLocal;
}
