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


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final EstablishmentRopository establishmentRopository;


    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {

        List<Employee> employee = employeeRepository.findAll();

        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employee));
    }

    @GetMapping("/{id}")

    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setPassaword(employeeRequest.password());
        employee.setEstablishment(establishmentRopository.findById(employeeRequest.fkEstablishment()).get());
        employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).get());
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employeeRepository.save(employee)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setPassaword(employeeRequest.password());
        employee.setEstablishment(establishmentRopository.findById(employeeRequest.fkEstablishment()).get());
        employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).get());
        employee.setId(id);
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employeeRepository.save(employee)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeRepository.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeResponse> login(@RequestBody EmployeeLogin employeeLogin) {
        Employee employee = employeeRepository.findByEmailAndPassaword(employeeLogin.email(), employeeLogin.password());
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employee));
    }
}