package school.sptech.neosspringjava.api.controllers.employeeController;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeDetailsDto;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;


@RestController
@RequestMapping("/employees")
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

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.partialUpdate(updates, id));
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
 
    @PostMapping("/login")
    public ResponseEntity<EmployeeTokenDto> login(@RequestBody EmployeeLogin employeeLogin) {
        EmployeeTokenDto employee = employeeService.authenticate(employeeLogin);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }
}
