package school.sptech.neosspringjava.api.dtos.clientDto;

import school.sptech.neosspringjava.domain.model.local.Local;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreatDTO {
    private String name;
    private String email;
    private String password;
    private Local local;
    private String profilePic;
}
