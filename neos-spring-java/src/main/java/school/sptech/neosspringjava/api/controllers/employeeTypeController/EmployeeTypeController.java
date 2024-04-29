package school.sptech.neosspringjava.api.controllers.employeeTypeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper.EmployeeTypeMapper;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;

@RestController
@RequestMapping("/employeeType")
@RequiredArgsConstructor
public class EmployeeTypeController {

    private final EmployeeTypeMapper employeeTypeMapper;
    private final EmployeeTypeRepository employeeTypeRepository;

    @GetMapping
    public ResponseEntity<List<EmployeeTypeResponse>> getEmployeeType() {
        return ResponseEntity.ok(employeeTypeMapper.toResponseList(employeeTypeRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> getEmployeeTypeById(Integer id)
    {
        return ResponseEntity.ok(employeeTypeMapper.toResponse(employeeTypeRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<EmployeeTypeResponse> createEmployeeType(EmployeeTypeRequest employeeTypeRequest) {
        return ResponseEntity.status(201).body(employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeTypeMapper.toEmployeeType(employeeTypeRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> updateEmployeeType(Integer id, EmployeeTypeRequest employeeTypeRequest) {
        EmployeeType employeeType = employeeTypeRepository.findById(id).get();
        employeeType.setName(employeeTypeRequest.name());
        return ResponseEntity.ok(employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeType)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeType(Integer id) {
        employeeTypeRepository.deleteById(id);
    }


 }