package school.sptech.neosspringjava.api.dtos.employee;

import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.model.status.Status;

public record EmployeeResponse(Integer id,String name,
        String email,
        String password,
        String imgUrl,
        Establishment establishment,
        Local fkLocal,
        Phone fkPhone,

        Status fkStatus,
        EmployeeType employeeType
) {

}
