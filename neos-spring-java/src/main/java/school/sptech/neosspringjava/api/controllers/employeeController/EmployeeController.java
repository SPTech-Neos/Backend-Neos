package school.sptech.neosspringjava.api.controllers.employeeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest employeeRequest) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.save(employeeRequest)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); 
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.update(employeeRequest, id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> partialUpdate(@RequestBody EmployeeRequest updates, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.partialUpdate(updates, id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findAll()));
    }

    @GetMapping("/active")
    public ResponseEntity<List<EmployeeResponse>> findAllActives() {
        return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findAllActives()));
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<EmployeeResponse>> findAllInactives() {
        return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findAllInactives()));
    }

    @GetMapping("/by-establishment/{id}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByEstablishment(@PathVariable Integer id) {
        return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findEmployeesByEstablishmentId(id)));
    }

    @PatchMapping("/deactive/{id}")
    public ResponseEntity<EmployeeResponse> deactive(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.deactivate(id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @PatchMapping("/reactive/{id}")
    public ResponseEntity<EmployeeResponse> reactive(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.reactivate(id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); 
        }
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeTokenDto> login(@RequestBody EmployeeLogin employeeLogin) {
        EmployeeTokenDto employee = employeeService.authenticate(employeeLogin);
        if (employee == null) {
            return ResponseEntity.status(404).build(); 
        }
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/by-establishment/{idEstab}/by-service/{idServ}")
    public ResponseEntity<List<EmployeeResponse>> findAllByEstablishmentAndService(@PathVariable Integer idEstab, @PathVariable Integer idServ) {
        return ResponseEntity.ok(EmployeeMapper.toEmployeeResponse(employeeService.findAllByEstablishmentAndService(idEstab, idServ)));
    }
}
