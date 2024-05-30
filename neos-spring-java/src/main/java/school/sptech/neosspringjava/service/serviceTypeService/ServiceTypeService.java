package school.sptech.neosspringjava.service.serviceTypeService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryRequest;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryResponse;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.api.mappers.serviceCategoryMapper.ServiceCategoryMapper;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.api.mappers.serviceTypeMapper.ServiceTypeMapper;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;
import school.sptech.neosspringjava.exception.NaoEncontradoException;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    public ServiceTypeResponse save(ServiceTypeRequest serviceTypeRequest) {
        try {
            ServiceType serviceType = new ServiceType();
            serviceType.setName(serviceTypeRequest.name());
            serviceType.setServiceCategory(serviceCategoryRepository.findById(serviceTypeRequest.fkServiceCategory()).orElseThrow());

            return serviceTypeMapper.toServiceTypeResponse(serviceTypeRepository.save(serviceType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o serviço: " + e.getMessage());
        }
    }

    public List<ServiceTypeResponse> findAll() {
        List<ServiceType> serviceType = serviceTypeRepository.findAll();
        return ServiceTypeMapper.toServiceResponseList(serviceType);
    }

    public ServiceTypeResponse findById(Integer id) {
        Optional<ServiceType> serviceTypeOp = serviceTypeRepository.findById(id);
        ServiceType serviceType = serviceTypeOp.get();
        return ServiceTypeMapper.toServiceTypeResponse(serviceType);
    }

    public ServiceTypeResponse update(ServiceTypeRequest serviceTypeRequest, Integer id) {

        ServiceType serviceType = serviceTypeRepository.findById(id).orElseThrow();

        if (serviceTypeRequest.fkServiceCategory() != null) {
            serviceType.setServiceCategory(serviceCategoryRepository.findById(serviceTypeRequest.fkServiceCategory()).orElseThrow(() ->
                    new NaoEncontradoException("ServiceCategory not found with id " + serviceTypeRequest.fkServiceCategory())));
        }

        serviceType.setName(serviceTypeRequest.name());


        return ServiceTypeMapper.toServiceTypeResponse(serviceTypeRepository.save(serviceType));
    }

    public String deleteByid(Integer id) {
        ServiceTypeResponse str = findById(id);
        if (str == null) {
            return "id não encontrado";
        } else {
            serviceTypeRepository.deleteById(id);
            return "tipo de serviço excluido";
        }
    }

}
