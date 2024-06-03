package school.sptech.neosspringjava.service.serviceService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper ServiceMapper;
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceResponse save(ServiceRequest serviceRequest) {
       Service service = new Service();
         service.setSpecification(serviceRequest.specification());
            service.setImgUrl(serviceRequest.imgUrl());
            service.setServiceType(serviceTypeRepository.findById(serviceRequest.serviceType()).get());
            serviceRepository.save(service);
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

    public ServiceResponse update(Integer id, ServiceRequest serviceRequest) {
        Optional<Service> serviceOp = serviceRepository.findById(id);
        Service service = serviceOp.get();
        service.setSpecification(serviceRequest.specification());
        service.setImgUrl(serviceRequest.imgUrl());
        service.setServiceType(serviceTypeRepository.findById(serviceRequest.serviceType()).get());
        serviceRepository.save(service);
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

