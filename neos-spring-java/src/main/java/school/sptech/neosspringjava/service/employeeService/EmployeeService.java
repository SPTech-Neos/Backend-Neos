package school.sptech.neosspringjava.service.employeeService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final EmployeeRepository employeeRepository;
   private final EmployeeMapper employeeMapper;
   private final EstablishmentRopository establishmentRopository;
   private final EmployeeTypeRepository employeeTypeRepository;

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        if (employeeRequest.employeeType() == null) {
            throw new IllegalArgumentException("Employee type must not be null");
        }

        EmployeeType employeeType = employeeTypeRepository.findById(employeeRequest.employeeType())
                .orElseThrow(() -> new RuntimeException("Employee type not found"));

        Employee employee = new Employee();
        employee.setEmail(employeeRequest.email());
        employee.setEmployeeType(employeeType);
        employee.setEstablishment(establishmentRopository.findById(employeeRequest.fkEstablishment())
                .orElseThrow(() -> new RuntimeException("Establishment not found")));
        employee.setImgUrl(employeeRequest.imgUrl());
        employee.setName(employeeRequest.name());
        employee.setPassword(employeeRequest.password());

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public EmployeeResponse update(EmployeeRequest employeeRequest, Integer id) {
       
        Employee employee = employeeRepository.findById(id).orElseThrow();
            employee.setName(employeeRequest.name());
            employee.setEmail(employeeRequest.email());
            employee.setPassword(employeeRequest.password());
            employee.setImgUrl(employeeRequest.imgUrl());
            employee.setEstablishment(establishmentRopository.findById(employeeRequest.fkEstablishment()).orElseThrow());
            employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.employeeType()).orElseThrow());
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
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
