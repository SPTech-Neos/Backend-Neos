package school.sptech.neosspringjava.controller;


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

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.employee.Employee;
import school.sptech.neosspringjava.repository.EmployeeRepository;


@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> listEmployee() {
        List<Employee> lstEmployee = employeeRepository.findAll();
        return lstEmployee.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstEmployee);
    }

  

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(201).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(id).orElse(null);
        if (employeeToUpdate == null) {
            return ResponseEntity.status(204).build();
        }
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setPassaword(employee.getPassaword());
        employeeToUpdate.setFkEstablishment(employee.getFkEstablishment());
        employeeToUpdate.setFkEmployeeType(employee.getFkEmployeeType());
        employeeRepository.save(employeeToUpdate);
        return ResponseEntity.status(200).body(employeeToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
        
        Employee employeeToDelete = employeeRepository.findById(id).orElse(null);
        if (employeeToDelete == null) {
            return ResponseEntity.status(204).build();
        }
        employeeRepository.delete(employeeToDelete);
        return ResponseEntity.status(200).body(employeeToDelete);
    }




    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findByEmailAndPassword(employeeRequest.getEmail(), employeeRequest.getPassword());
        return employee == null ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(employee);
    }


    public class EmployeeRequest {
       @Getter @Setter private String email;
       @Getter @Setter private String password;
    }

}
