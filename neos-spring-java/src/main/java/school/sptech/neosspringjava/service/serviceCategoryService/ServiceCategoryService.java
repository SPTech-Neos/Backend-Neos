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

    public ServiceCategoryResponse save(ServiceCategoryRequest serviceCategoryRequest) {
        ServiceCategory serviceCategory;
        if (serviceCategoryRequest.id() != null) {
            serviceCategory = ServiceCategory.builder().id(serviceCategoryRequest.id()).name(serviceCategoryRequest.name()).build();
            serviceCategoryRepository.save(serviceCategory);
        } else {
            serviceCategory = null;
        }
        return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
    }

    public List<ServiceCategoryResponse> findAll() {
        List<ServiceCategory> serviceCategory = serviceCategoryRepository.findAll();
        return ServiceCategoryMapper.toServiceResponseList(serviceCategory);
    }

    public ServiceCategoryResponse findById(Integer id) {
        Optional<ServiceCategory> serviceCategoryOp = serviceCategoryRepository.findById(id);
        ServiceCategory serviceCategory = serviceCategoryOp.get();
        return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
    }

    public String deleteByid(Integer id) {
        ServiceCategoryResponse str = findById(id);
        if (str == null) {
            return "id não encontrado";
        } else {
            serviceCategoryRepository.deleteById(id);
            return "tipo de serviço excluido";
        }
    }

}

