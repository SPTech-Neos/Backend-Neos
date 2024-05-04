package school.sptech.neosspringjava.api.dtos.clientDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTokenDto {
    private Integer clientId;
    private  String name;
    private String email;
    private  String token;


}
