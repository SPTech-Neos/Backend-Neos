package school.sptech.neosspringjava.api.dtos.employee;

import java.util.List;

import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.domain.model.EmployeeType;
import school.sptech.neosspringjava.domain.model.Establishment;

public record EmployeeRelacionamento(
    Integer id,String name,
        String email,
        String password,
        String imgUrl,
        Establishment establishment,
        EmployeeType employeeType,
        List<ServiceResponse> services
) {

}
