package school.sptech.neosspringjava.entity;

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
@Table(name = "endereco")
public class Endereco {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    @Getter
    @Setter
    @NotEmpty
    @NotBlank(message = "logradouro é obrigatório")
    private String logradouro;
    @Getter
    @Setter
    @NotEmpty
    @NotBlank(message = "cidade é obrigatório")
    private String cidade;
    @Getter
    @Setter
    @NotEmpty
    @NotBlank(message = "estado é obrigatório")
    private String estado;
}