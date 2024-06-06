package school.sptech.neosspringjava.api.controllers.employeeServicesController;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.api.mappers.employeeServicesMapper.EmployeeServicesMapper;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.service.EmployeeServService.EmployeeServService;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;

@RestController
@RequestMapping("/employeeServices")
@RequiredArgsConstructor
public class EmployeeServicesController {

   private final EmployeeServService employeeServService;

    @PostMapping
    public ResponseEntity<EmployeeServicesResponse> save(@RequestBody EmployeeServicesRequest employeeServicesRequest) {
        return ResponseEntity.ok(employeeServService.save(employeeServicesRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeServicesResponse> update(@RequestBody EmployeeServicesRequest employeeServicesRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeServService.update(employeeServicesRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeServService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeServicesResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeServService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeServicesResponse>> findAll() {
        return ResponseEntity.ok(employeeServService.findAll());
    }
    
   
}
