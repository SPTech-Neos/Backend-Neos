package school.sptech.neosspringjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper;
import school.sptech.neosspringjava.domain.model.EmployeeType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository;

@Service@RequiredArgsConstructor
public class EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;
    private final EmployeeTypeMapper employeeTypeMapper;

    public EmployeeType save(EmployeeTypeRequest employeeTypeRequest) {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setName(employeeTypeRequest.name());
        return employeeTypeRepository.save(employeeType);
    }

    public EmployeeType update(EmployeeTypeRequest employeeTypeRequest, Integer id) {
        EmployeeType employeeType = employeeTypeRepository.findById(id).orElseThrow();
        employeeType.setName(employeeTypeRequest.name());
        return employeeTypeRepository.save(employeeType);
    }

    public void delete(Integer id) {
        employeeTypeRepository.deleteById(id);
    }

    public EmployeeType findById(Integer id) {
        return employeeTypeRepository.findById(id).orElseThrow( () -> new RuntimeException("Tipo de funcionário não encontrado"));
    }

    public List<EmployeeType> findAll() {
        return employeeTypeRepository.findAll();
    }

    

}
