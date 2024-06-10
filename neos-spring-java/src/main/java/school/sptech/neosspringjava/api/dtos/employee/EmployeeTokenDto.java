package school.sptech.neosspringjava.api.dtos.employee;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Getter
@Setter
public class EmployeeTokenDto {
    private Integer Id;
    private String name;
    private String email;
    private String imgUrl;
    private Establishment establishment;
    private EmployeeType employeeType;
    private String token;

}
