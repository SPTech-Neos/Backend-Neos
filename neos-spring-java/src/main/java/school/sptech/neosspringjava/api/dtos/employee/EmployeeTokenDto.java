package school.sptech.neosspringjava.api.dtos.employee;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.EmployeeType;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.model.Phone;
import school.sptech.neosspringjava.domain.model.Status;

@Getter
@Setter
public class EmployeeTokenDto {
    private Integer id;
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
