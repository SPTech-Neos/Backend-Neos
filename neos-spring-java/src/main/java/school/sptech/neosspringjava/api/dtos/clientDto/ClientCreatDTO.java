package school.sptech.neosspringjava.api.dtos.clientDto;

import school.sptech.neosspringjava.domain.model.local.Local;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientCreatDTO {
    private String name;
    @Email
    private String email;
    private String password;
    private Integer local;
    private String imgUrl;
    private Integer phone;
}
