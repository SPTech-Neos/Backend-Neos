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
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;


@RestController
@RequestMapping("/Employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employees));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setPassaword(employeeRequest.password());
        employee.setFkEstablishment(employeeRequest.fkEstablishment());
        employee.setFkEmployeeType(employeeRequest.fkEmployeeType());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employeeMapper.toEmployeeResponse(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        employeeRepository.delete(employee);
        return ResponseEntity.noContent().build();
    }

}
