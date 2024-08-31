package school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;

@Component
public class EmployeeTypeMapper {

    public static EmployeeTypeResponse toResponse(EmployeeType employeeType) {
        return new EmployeeTypeResponse(employeeType.getId(), employeeType.getName());
    }

    public static List<EmployeeTypeResponse> toResponseList(List<EmployeeType> employeeTypes) {
        return employeeTypes.stream().map(EmployeeTypeMapper::toResponse).collect(Collectors.toList());
    }

    public static EmployeeType toEmployeeType(EmployeeTypeRequest employeeTypeRequest) {
        return EmployeeType.builder().name(employeeTypeRequest.name()).build();
    }




}
