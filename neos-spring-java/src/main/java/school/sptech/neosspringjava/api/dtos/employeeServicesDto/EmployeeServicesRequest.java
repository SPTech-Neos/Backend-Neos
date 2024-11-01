package school.sptech.neosspringjava.api.dtos.employeeServicesDto;

import java.sql.Date;

import lombok.Builder;

@Builder
public record EmployeeServicesRequest(Integer hoursSpent, Boolean expertise, Integer fkEmployee, Integer fkService) {

   
}
