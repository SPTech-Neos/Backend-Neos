package school.sptech.neosspringjava.api.dtos.employeeServicesDto;

import java.util.Date;

import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Service;

public record EmployeeServicesResponse(
        Integer id,
        Date hoursSpent,
        Boolean expertise,
        Employee employee,
        Service service
) {

}
