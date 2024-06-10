package school.sptech.neosspringjava.service.EmployeeServService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesRequest;
import school.sptech.neosspringjava.api.dtos.employeeServicesDto.EmployeeServicesResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.employeeServicesMapper.EmployeeServicesMapper;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServServiceTest {

    @Mock
    private EmployeeServicesRepository employeeServicesRepository;

    @Mock
    private EmployeeServicesMapper employeeServicesMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private EmployeeServService employeeServService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Employee employee = new Employee();
        Service service = new Service();
        EmployeeServices employeeServices = new EmployeeServices();
        EmployeeServicesResponse employeeServicesResponse = new EmployeeServicesResponse(null, null, null, employee, service);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(serviceRepository.findById(1)).thenReturn(Optional.of(service));
        when(employeeServicesRepository.save(any(EmployeeServices.class))).thenReturn(employeeServices);


    }

    @Test
    void testUpdate() {
        Employee employee = new Employee();
        Service service = new Service();
        EmployeeServices employeeServices = new EmployeeServices();
        EmployeeServicesResponse employeeServicesResponse = new EmployeeServicesResponse(null, null, null, employee, service);

        when(employeeServicesRepository.findById(1)).thenReturn(Optional.of(employeeServices));
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(serviceRepository.findById(1)).thenReturn(Optional.of(service));
        when(employeeServicesRepository.save(employeeServices)).thenReturn(employeeServices);

    }

    @Test
    void testDelete() {
        employeeServService.delete(1);

        verify(employeeServicesRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        EmployeeServices employeeServices = new EmployeeServices();
        EmployeeServicesResponse employeeServicesResponse = new EmployeeServicesResponse(null, null, null, null, null);

        when(employeeServicesRepository.findById(1)).thenReturn(Optional.of(employeeServices));

        EmployeeServicesResponse result = employeeServService.findById(1);

        assertEquals(employeeServicesResponse, result);
        verify(employeeServicesRepository).findById(1);
    }

    @Test
    void testFindAll() {
        List<EmployeeServices> employeeServicesList = Arrays.asList(new EmployeeServices(), new EmployeeServices());
        List<EmployeeServicesResponse> employeeServicesResponses = Arrays.asList(new EmployeeServicesResponse(null, null, null, null, null), new EmployeeServicesResponse(null, null, null, null, null));

        when(employeeServicesRepository.findAll()).thenReturn(employeeServicesList);

        List<EmployeeServicesResponse> result = employeeServService.findAll();

        assertEquals(employeeServicesResponses, result);
        verify(employeeServicesRepository).findAll();
    }

    @Test
    void testFindByEmployee() {
        Employee employee = new Employee();
        List<EmployeeServices> employeeServicesList = Arrays.asList(new EmployeeServices(), new EmployeeServices());
        var serviceResponse1 = new ServiceResponse(null, null, null, null);
        ServiceResponse serviceResponse2 = new ServiceResponse(null, null, null, null);
        List<ServiceResponse> serviceResponses = Arrays.asList(serviceResponse1, serviceResponse2);

        when(employeeServicesRepository.findAllByEmployee(employee)).thenReturn(employeeServicesList);

    }
}
