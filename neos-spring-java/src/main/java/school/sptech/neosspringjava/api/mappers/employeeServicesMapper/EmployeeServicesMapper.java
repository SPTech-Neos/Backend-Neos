package school.sptech.neosspringjava.api.mappers.employeeServicesMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;

@Component
public class EmployeeServicesMapper {

    public EmployeeServices toEmployeeServices(EmployeeServicesRequest employeeServicesRequest) {
        return EmployeeServices.builder()
                .hoursSpent(employeeServicesRequest.hoursSpent())
                .expertise(employeeServicesRequest.expertise())
                .fkEmployee(employeeServicesRequest.fkEmployee())
                .fkService(employeeServicesRequest.fkService())
                .build();
    }

    public EmployeeServicesResponse toEmployeeServicesResponse(EmployeeServices employeeServices) {
        return new EmployeeServicesResponse(employeeServices.getId(), employeeServices.getHoursSpent(),
                employeeServices.getExpertise(), employeeServices.getFkEmployee(), employeeServices.getFkService());
    }

    public List<EmployeeServicesResponse> toEmployeeServicesResponseList(List<EmployeeServices> employeeServices) {
        return employeeServices.stream().map(this::toEmployeeServicesResponse).collect(Collectors.toList());
    }
}
