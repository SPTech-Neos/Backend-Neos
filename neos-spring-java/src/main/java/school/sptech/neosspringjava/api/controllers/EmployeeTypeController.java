package school.sptech.neosspringjava.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeRequest;
import school.sptech.neosspringjava.api.dtos.EmployeeTypeDto.EmployeeTypeResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeTypeMapper;
import school.sptech.neosspringjava.domain.model.EmployeeType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository;
import school.sptech.neosspringjava.service.EmployeeTypeService;

@RestController
@RequestMapping("/employee-types")
@RequiredArgsConstructor
public class EmployeeTypeController {

   private final EmployeeTypeService employeeTypeService;

    @PostMapping
    public ResponseEntity<EmployeeTypeResponse> save(@RequestBody EmployeeTypeRequest employeeTypeRequest) {
        return ResponseEntity.ok(EmployeeTypeMapper.toResponse(employeeTypeService.save(employeeTypeRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> update(@RequestBody EmployeeTypeRequest employeeTypeRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(EmployeeTypeMapper.toResponse(employeeTypeService.update(employeeTypeRequest, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(EmployeeTypeMapper.toResponse(employeeTypeService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeTypeResponse>> findAll() {
        return ResponseEntity.ok(EmployeeTypeMapper.toResponseList(employeeTypeService.findAll()));
    }

 }