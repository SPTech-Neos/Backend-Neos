package school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;

@Component
public class EmployeeTypeMapper {

    public EmployeeTypeResponse toResponse(EmployeeType employeeType) {
        return new EmployeeTypeResponse(employeeType.getId(), employeeType.getName());
    }

    public List<EmployeeTypeResponse> toResponseList(List<EmployeeType> employeeTypes) {
        return employeeTypes.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EmployeeType toEmployeeType(EmployeeTypeRequest employeeTypeRequest) {
        return EmployeeType.builder().name(employeeTypeRequest.name()).build();
    }




}
