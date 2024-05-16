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
import school.sptech.neosspringjava.service.employeeTypeService.EmployeeTypeService;

@RestController
@RequestMapping("/employeeType")
@RequiredArgsConstructor
public class EmployeeTypeController {

   private final EmployeeTypeService employeeTypeService;

    @PostMapping
    public ResponseEntity<EmployeeTypeResponse> save(EmployeeTypeRequest employeeTypeRequest) {
        return ResponseEntity.ok(employeeTypeService.save(employeeTypeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> update(EmployeeTypeRequest employeeTypeRequest, Integer id) {
        return ResponseEntity.ok(employeeTypeService.update(employeeTypeRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Integer id) {
        employeeTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> findById(Integer id) {
        return ResponseEntity.ok(employeeTypeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeTypeResponse>> findAll() {
        return ResponseEntity.ok(employeeTypeService.findAll());
    }

 }