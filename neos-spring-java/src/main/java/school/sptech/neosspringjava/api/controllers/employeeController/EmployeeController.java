package school.sptech.neosspringjava.api.controllers.employeeController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {


private final EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.save(employeeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.update(employeeRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }
}

