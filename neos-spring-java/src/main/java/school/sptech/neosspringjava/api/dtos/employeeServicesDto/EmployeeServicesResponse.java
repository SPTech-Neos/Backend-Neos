package school.sptech.neosspringjava.api.dtos.employeeServicesDto;

import java.util.Date;

public record EmployeeServicesResponse(Integer id, Date hoursSpent, Boolean expertise, Integer fkEmployee, Integer fkService) {

}
