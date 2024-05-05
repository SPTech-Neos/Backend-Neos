package school.sptech.neosspringjava.api.controllers.employeeServicesController;



import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.api.mappers.employeeServicesMapper.EmployeeServicesMapper;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@RestController
@RequestMapping("/employeeServices")
@RequiredArgsConstructor
public class EmployeeServicesController {

    private final EmployeeServicesMapper employeeServicesMapper;
    private final EmployeeServicesRepository employeeServicesRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceRepository serviceRepository;
    
    @GetMapping
    public List<EmployeeServicesResponse> findAll() {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeServicesResponse findById(Integer id) {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.findById(id).get());
    }

    @PostMapping
    public EmployeeServicesResponse save(EmployeeServicesRequest employeeServicesRequest) {
        EmployeeServices employeeServices = EmployeeServices.builder()
                .hoursSpent(employeeServicesRequest.hoursSpent())
                .expertise(employeeServicesRequest.expertise())
                .employee(employeeRepository.findById(employeeServicesRequest.fkEmployee()).get())
                .service(serviceRepository.findById(employeeServicesRequest.fkService()).get())
                .build();
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServices));
    }

    @PutMapping("/{id}")
    public EmployeeServicesResponse update(Integer id, EmployeeServicesRequest employeeServicesRequest) {
        EmployeeServices employeeServices = EmployeeServices.builder()
                .id(id)
                .hoursSpent(employeeServicesRequest.hoursSpent())
                .expertise(employeeServicesRequest.expertise())
                .employee(employeeRepository.findById(employeeServicesRequest.fkEmployee()).get())
                .service(serviceRepository.findById(employeeServicesRequest.fkService()).get())
                .build();
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServices));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeServices(Integer id) {
        employeeServicesRepository.deleteById(id);
    }
}
