package school.sptech.neosspringjava.domain.model.company;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompany;
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty
    private String name;
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

}