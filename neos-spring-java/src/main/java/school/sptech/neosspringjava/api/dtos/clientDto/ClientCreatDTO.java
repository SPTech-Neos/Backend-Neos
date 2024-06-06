package school.sptech.neosspringjava.api.dtos.clientDto;

import school.sptech.neosspringjava.domain.model.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientCreatDTO {
    private String name;
    private String email;
    private String password;
    private Integer local;
    private String imgUrl;
}
