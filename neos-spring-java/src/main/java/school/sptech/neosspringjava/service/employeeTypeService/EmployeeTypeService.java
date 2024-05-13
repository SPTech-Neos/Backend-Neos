package school.sptech.neosspringjava.service.employeeTypeService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper.EmployeeTypeMapper;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;

@Service@RequiredArgsConstructor
public class EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;
    private final EmployeeTypeMapper employeeTypeMapper;

    public EmployeeTypeResponse save(EmployeeTypeRequest employeeTypeRequest) {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setName(employeeTypeRequest.name());
        return employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeType));
    }

    public EmployeeTypeResponse update(EmployeeTypeRequest employeeTypeRequest, Integer id) {
        EmployeeType employeeType = employeeTypeRepository.findById(id).orElseThrow();
        employeeType.setName(employeeTypeRequest.name());
        return employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeType));
    }

    public void delete(Integer id) {
        employeeTypeRepository.deleteById(id);
    }

    public EmployeeTypeResponse findById(Integer id) {
        return employeeTypeMapper.toResponse(employeeTypeRepository.findById(id).orElseThrow());
    }

    public List<EmployeeTypeResponse> findAll() {
        return employeeTypeMapper.toResponseList(employeeTypeRepository.findAll());
    }

    

}
