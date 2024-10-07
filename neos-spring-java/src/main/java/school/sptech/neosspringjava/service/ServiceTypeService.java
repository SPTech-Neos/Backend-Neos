package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;
import school.sptech.neosspringjava.api.mappers.ServiceTypeMapper;
import school.sptech.neosspringjava.domain.model.ServiceCategory;
import school.sptech.neosspringjava.domain.model.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceCategoryRepository;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    private final ServiceTypeMapper serviceTypeMapper;

    private final ServiceMapper serviceMapper;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public List<ServiceTypeResponse> findAll() {
        return serviceTypeMapper.toResponse(serviceTypeRepository.findAll());
    }

    public ServiceTypeResponse save(ServiceTypeRequest serviceTypeRequest) {
        ServiceType serviceType = new ServiceType();
        serviceType.setName(serviceTypeRequest.name());
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceTypeRequest.ServiceCategory()).get();
        serviceType.setServiceCategory(serviceCategory);
        
        return serviceTypeMapper.toResponse(serviceTypeRepository.save(serviceType));
        

    }

    public ServiceTypeResponse findById(Integer id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        return serviceTypeMapper.toResponse(serviceType.orElse(null));
    }

    public ServiceTypeResponse update(ServiceTypeRequest serviceTypeRequest, Integer id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        if (serviceType.isPresent()) {
            ServiceType serviceTypeUpdated = serviceType.get();
            serviceTypeUpdated.setName(serviceTypeRequest.name());
            ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceTypeRequest.ServiceCategory()).get();
            serviceTypeUpdated.setServiceCategory(serviceCategory);

            return serviceTypeMapper.toResponse(serviceTypeRepository.save(serviceTypeUpdated));
        }
        return null;
    }

    public void delete(Integer id) {
        serviceTypeRepository.deleteById(id);
    }

   



}
