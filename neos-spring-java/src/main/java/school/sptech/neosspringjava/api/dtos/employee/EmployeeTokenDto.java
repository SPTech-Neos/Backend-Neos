package school.sptech.neosspringjava.api.dtos.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeTokenDto {
    private Integer Id;
    private  String name;
    private String email;
    private  String token;
}
