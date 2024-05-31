package school.sptech.neosspringjava.api.controllers.employeeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
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

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id, @RequestBody EmployeeRequest employee) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        EmployeeResponse updatedEmployee = employeeService.update(employee, id);
        return ResponseEntity.ok(updatedEmployee);
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
    public ResponseEntity<EmployeeResponse> login(@RequestBody EmployeeLogin employeeLogin) {
        EmployeeResponse employee = employeeService.findByEmailAndPassword(employeeLogin.email(), employeeLogin.password());
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }
}
