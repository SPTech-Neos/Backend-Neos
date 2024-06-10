package school.sptech.neosspringjava.service.serviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ServiceServiceTest {

    @InjectMocks
    private ServiceService serviceService;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private ServiceTypeRepository serviceTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        ServiceRequest request = new ServiceRequest("specification", "imgUrl", 1);
        ServiceType serviceType = new ServiceType();
        Service service = new Service();
        service.setSpecification("specification");
        service.setImgUrl("imgUrl");
        service.setServiceType(serviceType);

        when(serviceTypeRepository.findById(anyInt())).thenReturn(Optional.of(serviceType));
        when(serviceRepository.save(any(Service.class))).thenReturn(service);
      
        ServiceResponse response = serviceService.save(request);

        assertNotNull(response);
        assertEquals("specification", response.specification());
        assertEquals("imgUrl", response.imgUrl());
    }

    @Test
    void testFindAll() {
        Service service = new Service();
    
    }

    @Test
    void testFindById() {
        Service service = new Service();
       
    }

    @Test
    void testUpdate() {
        ServiceRequest request = new ServiceRequest("newSpec", "newImgUrl", 1);
        ServiceType serviceType = new ServiceType();
        Service service = new Service();
    }

    @Test
    void testPartialUpdate() {
        Service service = new Service();
        ServiceType serviceType = new ServiceType();
       
    }

}