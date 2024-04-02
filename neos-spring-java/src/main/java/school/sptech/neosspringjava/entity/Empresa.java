package school.sptech.neosspringjava.entity;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty
    private String nome;
    @Getter
    @Setter
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

}