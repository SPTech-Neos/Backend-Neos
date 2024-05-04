package school.sptech.neosspringjava.api.dtos.employee;

import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public record EmployeeResponse(String name,
        String email,
        String password,
        Establishment establishment,
        EmployeeType employeeType) {

}
