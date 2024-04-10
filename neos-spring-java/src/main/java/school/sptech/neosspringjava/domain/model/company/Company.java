package school.sptech.neosspringjava.domain.model.company;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompany;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty
    private String name;
    @Getter
    @Setter
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

}