package school.sptech.neosspringjava.service.serviceTypeService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.api.mappers.serviceTypeMapper.ServiceTypeMapper;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeResponse save(ServiceTypeRequest serviceTypeRequest) {
        ServiceType serviceType;
        if (serviceTypeRequest.id() != null) {
            serviceType = ServiceType.builder().id(serviceTypeRequest.id()).name(serviceTypeRequest.name())
                    .ServiceCategory(serviceTypeRequest.ServiceCategory()).build();
            serviceTypeRepository.save(serviceType);
        } else {
            serviceType = ServiceType.builder().name(serviceTypeRequest.name())
                    .ServiceCategory(serviceTypeRequest.ServiceCategory()).build();
            serviceTypeRepository.save(serviceType);
        }
        return ServiceTypeMapper.toServiceTypeResponse(serviceType);
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
