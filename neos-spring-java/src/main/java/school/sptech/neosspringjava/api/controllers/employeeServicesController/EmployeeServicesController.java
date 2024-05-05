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

@RestController
@RequestMapping("/employeeServices")
@RequiredArgsConstructor
public class EmployeeServicesController {

    private final EmployeeServicesMapper employeeServicesMapper;
    private final EmployeeServicesRepository employeeServicesRepository;

    @GetMapping
    public List<EmployeeServicesResponse> getEmployeeServices() {
        return employeeServicesMapper.toEmployeeServicesResponseList(employeeServicesRepository.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeServicesResponse getEmployeeServicesById(Integer id) {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.findById(id).get());
    }

    @PostMapping
    public EmployeeServicesResponse createEmployeeServices(EmployeeServicesRequest employeeServicesRequest) {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServicesMapper.toEmployeeServices(employeeServicesRequest)));
    }
    
    @PutMapping("/{id}")
    public EmployeeServicesResponse updateEmployeeServices(Integer id, EmployeeServicesRequest employeeServicesRequest) {
        EmployeeServices employeeServices = employeeServicesRepository.findById(id).get();
        employeeServices.setHoursSpent(employeeServicesRequest.hoursSpent());
        employeeServices.setExpertise(employeeServicesRequest.expertise());
        employeeServices.setFkEmployee(employeeServicesRequest.fkEmployee());
        employeeServices.setFkService(employeeServicesRequest.fkService());
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServices));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeServices(Integer id) {
        employeeServicesRepository.deleteById(id);
    }
}
