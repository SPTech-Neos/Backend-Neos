package school.sptech.neosspringjava.api.mappers.employeeServicesMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;

@Component
public class EmployeeServicesMapper {

    public static EmployeeServicesResponse toEmployeeServicesResponse(EmployeeServices employeeServices) {
        return new EmployeeServicesResponse(employeeServices.getId(), employeeServices.getHoursSpent(), employeeServices.getExpertise(), employeeServices.getEmployee(), employeeServices.getService());
    }

    public static List<EmployeeServicesResponse> toEmployeeServicesResponse(List<EmployeeServices> employeeServices) {
        return employeeServices.stream().map(EmployeeServicesMapper::toEmployeeServicesResponse).collect(Collectors.toList());
    }
}
