package school.sptech.neosspringjava.api.mappers.employeeMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.domain.model.employee.Employee;

@Component
public class EmployeeMapper {

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(),employee.getName(), employee.getEmail(), employee.getPassword(), employee.getImgUrl(), employee.getEstablishment(), employee.getEmployeeType());
    }

    public static List<EmployeeResponse> toEmployeeResponse(List<Employee> employee) {
        return employee.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }

 

 

}
