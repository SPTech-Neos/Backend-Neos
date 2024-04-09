package school.sptech.neosspringjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class EmployeeType {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome do tipo de funcionario é obrigatório")
    @NotEmpty(message = "Nome do tipo de funcionario é obrigatório")
    private String name;
    @Override
    public String toString() {
        return "EmployeeType [id=" + id + ", name=" + name + "]";
    }
}
