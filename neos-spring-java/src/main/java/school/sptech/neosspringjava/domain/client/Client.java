package school.sptech.neosspringjava.domain.client;

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
public class Client {

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
    private Integer fkLocal;
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", email=" + email + ", passaword=" + passaword + ", fkLocal="
                + fkLocal + "]";
    }


    
}
