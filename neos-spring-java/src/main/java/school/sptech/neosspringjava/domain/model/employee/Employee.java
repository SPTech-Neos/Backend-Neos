
package school.sptech.neosspringjava.domain.model.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    @Email
    private String email;
    @NotBlank(message = "criar uma senha é obrigatório")
    @NotEmpty(message = "criar uma senha é obrigatório")
    private String passaword;
    @NotBlank(message = "um funcionário precisa de um estabelecimento")
    @NotEmpty(message = "um funcionário precisa de um estabelecimento")
    private Integer fkEstablishment;
    @NotBlank(message = "um funcionário precisa de um estabelecimento")
    @NotEmpty(message = "um funcionário precisa de um estabelecimento")
    private Integer fkEmployeeType;


    

}
