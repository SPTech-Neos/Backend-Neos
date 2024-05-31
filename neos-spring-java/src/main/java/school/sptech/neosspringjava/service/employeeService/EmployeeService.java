package school.sptech.neosspringjava.service.employeeService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.exception.NaoEncontradoException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final EmployeeRepository employeeRepository;
   private final EmployeeMapper employeeMapper;
   private final EstablishmentRepository establishmentRepository;
   private final EmployeeTypeRepository employeeTypeRepository;

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
         Employee employee = new Employee();
            employee.setName(employeeRequest.name());
            employee.setEmail(employeeRequest.email());
            employee.setPassword(employeeRequest.password());
            employee.setEstablishment(establishmentRepository.findById(employeeRequest.fkEstablishment()).orElseThrow());

            employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).orElseThrow());
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }


    public EmployeeResponse update(EmployeeRequest employeeRequest, Integer id) {

        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();

            if (employeeRequest.name() != null) {
                employee.setName(employeeRequest.name());
            }

            if (employeeRequest.email() != null) {
                employee.setEmail(employeeRequest.email());
            }

            if (employeeRequest.password() != null) {
                employee.setPassword(employeeRequest.password());
            }

            if (employeeRequest.fkEstablishment() != null) {
                employee.setEstablishment(establishmentRepository.findById(employeeRequest.fkEstablishment()).orElseThrow(() ->
                        new NaoEncontradoException("Establishment not found with id " + employeeRequest.fkEstablishment())));
            }

            if (employeeRequest.fkEmployeeType() != null) {
                employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.fkEmployeeType()).orElseThrow(() ->
                        new NaoEncontradoException("EmployeeType not found with id " + employeeRequest.fkEmployeeType())));
            }

            return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
        } else {
            throw new NaoEncontradoException("Employee not found with id " + id);
        }
    }

    public void delete(Integer id) {
         employeeRepository.deleteById(id);
    }

    public EmployeeResponse findById(Integer id) {
         return employeeMapper.toEmployeeResponse(employeeRepository.findById(id).orElseThrow());
    }

    public EmployeeResponse findByEmailAndPassword(String email, String password) {
        return employeeMapper.toEmployeeResponse(employeeRepository.findByEmailAndPassword(email, password));
    }


    public List<EmployeeResponse> findAll() {
         return employeeMapper.toEmployeeResponse(employeeRepository.findAll());
    }
    
    

}
