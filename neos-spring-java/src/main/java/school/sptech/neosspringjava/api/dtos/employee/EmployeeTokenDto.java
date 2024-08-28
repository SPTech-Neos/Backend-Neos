package school.sptech.neosspringjava.api.dtos.employee;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.model.status.Status;

@Getter
@Setter
public class EmployeeTokenDto {
    private Integer Id;
    private String name;
    private String email;
    private String imgUrl;
    private Establishment establishment;
    private Local local;
    private EmployeeType employeeType;
    private Phone phone;
    private Status status;
    private String token;

}
