package school.sptech.neosspringjava.api.dtos.employee;

import school.sptech.neosspringjava.domain.model.EmployeeType;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.model.Phone;
import school.sptech.neosspringjava.domain.model.Status;

public record EmployeeResponse(Integer id,String name,
        String email,
        String password,
        String imgUrl,
        Establishment establishment,
        Local Local,
        Phone Phone,
        Status Status,
        EmployeeType employeeType
) {

}
