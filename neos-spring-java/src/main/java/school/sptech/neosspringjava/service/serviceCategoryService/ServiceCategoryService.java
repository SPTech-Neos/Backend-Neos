package school.sptech.neosspringjava.service.serviceCategoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryRequest;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceCategoryMapper.ServiceCategoryMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;

@Service
@RequiredArgsConstructor
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceCategoryMapper serviceCategoryMapper;

    public ServiceCategoryResponse save(ServiceCategoryRequest serviceCategoryRequest) {
        try {
            ServiceCategory serviceCategory = new ServiceCategory();
            serviceCategory.setName(serviceCategoryRequest.name());

            return serviceCategoryMapper.toServiceCategoryResponse(serviceCategoryRepository.save(serviceCategory));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o serviço: " + e.getMessage());
        }
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

    public ServiceCategoryResponse update(ServiceCategoryRequest serviceCategoryRequest, Integer id) {

        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id).orElseThrow();
        serviceCategory.setName(serviceCategoryRequest.name());

        return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategoryRepository.save(serviceCategory));
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

