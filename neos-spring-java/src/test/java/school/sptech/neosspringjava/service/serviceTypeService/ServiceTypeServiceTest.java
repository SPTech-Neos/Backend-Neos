package school.sptech.neosspringjava.service.serviceTypeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.api.mappers.serviceTypeMapper.ServiceTypeMapper;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;

import java.util.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceTypeServiceTest {

    @Mock
    private ServiceTypeRepository serviceTypeRepository;

    @Mock
    private ServiceTypeMapper serviceTypeMapper;

    @Mock
    private ServiceCategoryRepository serviceCategoryRepository;

    @InjectMocks
    private ServiceTypeService serviceTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        doNothing().when(serviceTypeRepository).deleteById(id);

        serviceTypeService.delete(id);

        verify(serviceTypeRepository, times(1)).deleteById(id);
    }


    @Test
    void testFindById() {
        Integer id = 1;
        ServiceType serviceType = new ServiceType();
        ServiceTypeResponse serviceTypeResponse = new ServiceTypeResponse(id, "Test Service", new ServiceCategory());
        
        when(serviceTypeRepository.findById(id)).thenReturn(Optional.of(serviceType));
        when(serviceTypeMapper.toResponse(serviceType)).thenReturn(serviceTypeResponse);

        ServiceTypeResponse response = serviceTypeService.findById(id);

        assertNotNull(response);
        assertEquals(id, response.id());
        verify(serviceTypeRepository, times(1)).findById(id);
        verify(serviceTypeMapper, times(1)).toResponse(serviceType);
    }

    @Test
    void testSave() {
        ServiceTypeRequest serviceTypeRequest = new ServiceTypeRequest("Test Service", 1);
        ServiceCategory serviceCategory = new ServiceCategory();
        ServiceType serviceType = new ServiceType();
        ServiceTypeResponse serviceTypeResponse = new ServiceTypeResponse(1, "Test Service", serviceCategory);

        when(serviceCategoryRepository.findById(serviceTypeRequest.ServiceCategory())).thenReturn(Optional.of(serviceCategory));
        when(serviceTypeRepository.save(any(ServiceType.class))).thenReturn(serviceType);
        when(serviceTypeMapper.toResponse(serviceType)).thenReturn(serviceTypeResponse);

        ServiceTypeResponse response = serviceTypeService.save(serviceTypeRequest);

        assertNotNull(response);
        assertEquals(serviceTypeRequest.name(), response.name());
        verify(serviceCategoryRepository, times(1)).findById(serviceTypeRequest.ServiceCategory());
        verify(serviceTypeRepository, times(1)).save(any(ServiceType.class));
        verify(serviceTypeMapper, times(1)).toResponse(serviceType);
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        ServiceTypeRequest serviceTypeRequest = new ServiceTypeRequest("Updated Service", 1);
        ServiceCategory serviceCategory = new ServiceCategory();
        ServiceType serviceType = new ServiceType();
        ServiceTypeResponse serviceTypeResponse = new ServiceTypeResponse(id, "Updated Service", serviceCategory);

        when(serviceTypeRepository.findById(id)).thenReturn(Optional.of(serviceType));
        when(serviceCategoryRepository.findById(serviceTypeRequest.ServiceCategory())).thenReturn(Optional.of(serviceCategory));
        when(serviceTypeRepository.save(any(ServiceType.class))).thenReturn(serviceType);
        when(serviceTypeMapper.toResponse(serviceType)).thenReturn(serviceTypeResponse);

        ServiceTypeResponse response = serviceTypeService.update(serviceTypeRequest, id);

        assertNotNull(response);
        assertEquals(serviceTypeRequest.name(), response.name());
        verify(serviceTypeRepository, times(1)).findById(id);
        verify(serviceCategoryRepository, times(1)).findById(serviceTypeRequest.ServiceCategory());
        verify(serviceTypeRepository, times(1)).save(serviceType);
        verify(serviceTypeMapper, times(1)).toResponse(serviceType);
    }
}
