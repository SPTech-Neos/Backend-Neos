package school.sptech.neosspringjava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.EmployeeServicesMapper;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.EmployeeServices;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServService {
    private final EmployeeServicesRepository employeeServicesRepository;
    private final ServiceService sService;
    private final EmployeeServicesMapper employeeServicesMapper;
    private final EmployeeRepository    employeeRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public EmployeeServicesResponse save(EmployeeServicesRequest employeeServicesRequest) {
        EmployeeServices employeeServices = new EmployeeServices();
        employeeServices.setHoursSpent(employeeServicesRequest.hoursSpent());
        employeeServices.setExpertise(employeeServicesRequest.expertise());
        employeeServices.setEmployee(employeeRepository.findById(employeeServicesRequest.fkEmployee()).orElseThrow());
        employeeServices.setService(serviceRepository.findById(employeeServicesRequest.fkService()).orElseThrow());
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServices));
    }

    public EmployeeServicesResponse update(EmployeeServicesRequest employeeServicesRequest, Integer id) {
        EmployeeServices employeeServices = employeeServicesRepository.findById(id).orElseThrow();
        employeeServices.setHoursSpent(employeeServicesRequest.hoursSpent());
        employeeServices.setExpertise(employeeServicesRequest.expertise());
        employeeServices.setEmployee(employeeRepository.findById(employeeServicesRequest.fkEmployee()).orElseThrow());
        employeeServices.setService(serviceRepository.findById(employeeServicesRequest.fkService()).orElseThrow());
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.save(employeeServices));
    }
    
    
    public void delete(Integer id) {
        employeeServicesRepository.deleteById(id);
    }

    public EmployeeServicesResponse findById(Integer id) {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.findById(id).orElseThrow());
    }

    public List<EmployeeServicesResponse> findAll() {
        return employeeServicesMapper.toEmployeeServicesResponse(employeeServicesRepository.findAll());
    }

    public List<ServiceResponse> findByEmployee(Employee employee) {
        List<EmployeeServices> employeeServices = employeeServicesRepository.findAllByEmployee(employee);

        List<ServiceResponse> services = new ArrayList<>();
        for (EmployeeServices employeeService : employeeServices) {
            services.add(serviceMapper.toServiceResponse(employeeService.getService()));
        }

        return services;
    }

    public List<EmployeeServices> findByService(Integer id){
            return employeeServicesRepository.findAllByService(sService.findById(id));
    }
    
}
