package school.sptech.neosspringjava.api.dtos.employeeServicesDto;

import java.util.Date;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;

public record EmployeeServicesResponse(Integer id, Date hoursSpent, Boolean expertise, Employee employee, Service service) {

}
