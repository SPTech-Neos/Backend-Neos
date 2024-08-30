package school.sptech.neosspringjava.domain.model.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @Email
    private String email;

    @NotBlank(message = "Criar uma senha é obrigatório")
    @NotEmpty(message = "Criar uma senha é obrigatório")
    @NotNull(message = "Criar uma senha é obrigatório")
    private String password;

    private String imgUrl;
    private String cpf;


    @ManyToOne
    @JoinColumn(name = "fkLocal", referencedColumnName = "local_id")
    private Local local;

    @OneToOne
    @JoinColumn(name = "fkPhone", referencedColumnName = "phone_id")
    private Phone phone;

}
