package school.sptech.neosspringjava.service.serviceService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceResponse save(ServiceRequest serviceRequest) {
        Service service;
        if (serviceRequest.id() != null) {
            service = Service.builder().id(serviceRequest.id()).specification(serviceRequest.specification())
                    .serviceType(serviceRequest.serviceType()).build();
            serviceRepository.save(service);
        } else {
            service = Service.builder().specification(serviceRequest.specification())
                    .serviceType(serviceRequest.serviceType()).build();
            serviceRepository.save(service);
        }
        return ServiceMapper.toServiceResponse(service);
    }

    public List<ServiceResponse> findAll() {
        List<Service> service = serviceRepository.findAll();
        return ServiceMapper.toServiceResponseList(service);
    }

    public ServiceResponse findById(Integer id) {
        Optional<Service> serviceOp = serviceRepository.findById(id);
        Service service = serviceOp.get();
        return ServiceMapper.toServiceResponse(service);
    }

    public String deleteByid(Integer id) {
        ServiceResponse str = findById(id);
        if (str == null) {
            return "id não encontrado";
        } else {
            serviceRepository.deleteById(id);
            return "tipo de serviço excluido";
        }
    }

}

