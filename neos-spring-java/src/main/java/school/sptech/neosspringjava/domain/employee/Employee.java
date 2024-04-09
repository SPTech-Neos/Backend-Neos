
package school.sptech.neosspringjava.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Employee {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    @Getter
    @Setter
    @Email
    private String email;
    @Getter
    @Setter
    @NotBlank(message = "criar uma senha é obrigatório")
    @NotEmpty(message = "criar uma senha é obrigatório")
    private String passaword;
    @Getter
    @Setter
    @NotBlank(message = "um funcionário precisa de um estabelecimento")
    @NotEmpty(message = "um funcionário precisa de um estabelecimento")
    private Integer fkEstablishment;
    @Getter
    @Setter
    @NotBlank(message = "um funcionário precisa de um estabelecimento")
    @NotEmpty(message = "um funcionário precisa de um estabelecimento")
    private Integer fkEmployeeType;


    

}
