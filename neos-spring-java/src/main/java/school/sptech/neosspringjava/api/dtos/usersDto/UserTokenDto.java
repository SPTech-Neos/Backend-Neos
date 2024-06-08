package school.sptech.neosspringjava.api.dtos.usersDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTokenDto {
    private Integer userId;
    private  String name;
    private String email;
    private  String token;
}
