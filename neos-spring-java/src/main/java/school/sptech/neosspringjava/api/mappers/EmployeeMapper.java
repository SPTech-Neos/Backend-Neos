package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.domain.model.employee.Employee;

@Component
public class EmployeeMapper {

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPassaword(),
                employee.getFkEstablishment(), employee.getFkEmployeeType());
    }

    public static List<EmployeeResponse> toEmployeeResponse(List<Employee> employees) {
        return employees.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }


    public static Employee toEmployee(EmployeeRequest employeeRequest) {
       Employee employee = new Employee();
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setPassaword(employeeRequest.password());
        employee.setFkEstablishment(employeeRequest.fkEstablishment());
        employee.setFkEmployeeType(employeeRequest.fkEmployeeType());
        return employee;
    }

 

 

}
