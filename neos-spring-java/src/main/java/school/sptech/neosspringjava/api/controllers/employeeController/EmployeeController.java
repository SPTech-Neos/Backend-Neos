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
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;


@RestController
@RequestMapping("/Employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final EstablishmentRopository establishmentRopository;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(employeeMapper.toEmployeeResponse(employeeRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeMapper.toEmployeeResponse(employeeRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .name(employeeRequest.name())
                .email(employeeRequest.email())
                .passaword(employeeRequest.password())
                .establishment(establishmentRopository.findById(employeeRequest.fkEstablishment()).get())
                .employeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).get())
                .build();
        return ResponseEntity.ok(employeeMapper.toEmployeeResponse(employeeRepository.save(employee)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setPassaword(employeeRequest.password());
        employee.setEstablishment(establishmentRopository.findById(employeeRequest.fkEstablishment()).get());
        employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).get());
        return ResponseEntity.ok(employeeMapper.toEmployeeResponse(employeeRepository.save(employee)));
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
