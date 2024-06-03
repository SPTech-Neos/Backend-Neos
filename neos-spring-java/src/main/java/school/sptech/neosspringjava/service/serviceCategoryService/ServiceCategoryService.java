package school.sptech.neosspringjava.service.serviceCategoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryRequest;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryResponse;
import school.sptech.neosspringjava.api.mappers.serviceCategoryMapper.ServiceCategoryMapper;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;

@Service
@RequiredArgsConstructor
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    public List<ServiceCategoryResponse> findAll() {
        List<ServiceCategory> serviceCategories = serviceCategoryRepository.findAll();
        return ServiceCategoryMapper.toServiceResponseList(serviceCategories);
    }

    public ServiceCategoryResponse save(ServiceCategoryRequest serviceCategoryRequest) {
        ServiceCategory serviceCategory = ServiceCategoryMapper.toServiceCategory(serviceCategoryRequest);
        serviceCategory = serviceCategoryRepository.save(serviceCategory);
        return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
    }

    public ServiceCategoryResponse update(ServiceCategoryRequest serviceCategoryRequest, Integer id) {
        Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(id);
        if (serviceCategoryOptional.isPresent()) {
            ServiceCategory serviceCategory = serviceCategoryOptional.get();
            serviceCategory.setName(serviceCategoryRequest.name());
            serviceCategory = serviceCategoryRepository.save(serviceCategory);
            return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
        }
        return null;
    }

    public void delete(Integer id) {
        serviceCategoryRepository.deleteById(id);

    }

    public ServiceCategoryResponse findById(Integer id) {
        Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(id);
        if (serviceCategoryOptional.isPresent()) {
            ServiceCategory serviceCategory = serviceCategoryOptional.get();
            return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
        }
        return null;
    }

    

}

