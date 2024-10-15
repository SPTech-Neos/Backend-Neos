package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDto.ClientTokenDto;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;

@Component
public class EmployeeMapper {

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(),employee.getName(), employee.getEmail(), employee.getPassword(), employee.getImgUrl(), employee.getEstablishment(), employee.getLocal(), employee.getPhone(), employee.getStatus(), employee.getEmployeeType());
    }

    public static List<EmployeeResponse> toEmployeeResponse(List<Employee> employee) {
        return employee.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }

    public static Employee toEntity(EmployeeRequest eDto, String passwordEncrypted){
        Employee e = new Employee();

        e.setName(eDto.name());
        e.setEmail(eDto.email());
        e.setPassword(passwordEncrypted);
        e.setImgUrl(eDto.imgUrl());

        return e;
    }

    public static EmployeeTokenDto of(Employee employee, String token){
            EmployeeTokenDto employeeTokenDto = new EmployeeTokenDto();

        employeeTokenDto.setId(employee.getId());
        employeeTokenDto.setEmail(employee.getEmail());
        employeeTokenDto.setName(employee.getName());
        employeeTokenDto.setImgUrl(employee.getImgUrl());
        employeeTokenDto.setEstablishment(employee.getEstablishment());
        employeeTokenDto.setLocal(employee.getLocal());
        employeeTokenDto.setEmployeeType(employee.getEmployeeType());
        employeeTokenDto.setToken(token);

        return employeeTokenDto;
    }

}
