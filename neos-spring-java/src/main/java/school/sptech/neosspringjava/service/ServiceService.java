package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;
import school.sptech.neosspringjava.domain.model.Service;
import school.sptech.neosspringjava.domain.model.ServiceType;
import school.sptech.neosspringjava.domain.model.Status;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper ServiceMapper;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusService statusService; 

    public ServiceResponse save(ServiceRequest serviceRequest) {

        Integer serviceTypeId = serviceRequest.serviceType();
        if (serviceTypeId == null) {
            throw new IllegalArgumentException("O ID do ServiceType não pode ser nulo.");
        }
    
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(() -> new IllegalArgumentException("ServiceType não encontrado com o ID: " + serviceTypeId));
    
        Service service = Service.builder()
                .specification(serviceRequest.specification())
                .aditumId(serviceRequest.aditumId())
                .price(serviceRequest.price())
                .imgUrl(serviceRequest.imgUrl())
                .serviceType(serviceType)
                .build();
    
        serviceRepository.save(service);
    
        return ServiceMapper.toServiceResponse(service);
    }
    
    public List<ServiceResponse> findAll() {
        List<Service> service = serviceRepository.findAll();
        return ServiceMapper.toServiceResponseList(service);
    }

    public Service findById(Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public ServiceResponse update(Integer id, ServiceRequest serviceRequest) {
        Service service = findById(id);
    
        service.setSpecification(serviceRequest.specification());
        service.setAditumId(serviceRequest.aditumId());
        service.setPrice(serviceRequest.price());
        service.setImgUrl(serviceRequest.imgUrl());
        service.setServiceType(serviceTypeRepository.findById(serviceRequest.serviceType())
                .orElseThrow(() -> new IllegalArgumentException("ServiceType não encontrado com o ID: " + serviceRequest.serviceType())));
    
        serviceRepository.save(service);
        return ServiceMapper.toServiceResponse(service);
    }
    

    public ServiceResponse partialUpdate(Integer id, ServiceRequest updates) {
        Service service = findById(id);
    
        if (updates.specification() != null) {
            service.setSpecification((String) updates.specification());
        }
    
        if (updates.aditumId() != null) {
            service.setAditumId((String) updates.aditumId());
        }
    
        if (updates.price()!= null) {
            service.setPrice((Double) updates.price());
        }
    
        if (updates.imgUrl() != null) {
            service.setImgUrl((String) updates.imgUrl());
        }
    
        if (updates.serviceType() != null) {
            Integer serviceTypeId = (Integer) updates.serviceType();
            ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId)
                    .orElseThrow(() -> new IllegalArgumentException("ServiceType não encontrado com o ID: " + serviceTypeId));
            service.setServiceType(serviceType);
        }

        if (updates.status() != null) {
            Integer statusId = (Integer) updates.status();
            Status status = statusService.findById(statusId);
            if (status == null) {
                throw new IllegalArgumentException("status não encontrado com o ID: " + statusId);
            }
            service.setStatus(status);
        }
    
        Service updatedService = serviceRepository.save(service);
        return ServiceMapper.toServiceResponse(updatedService);
    }
    
    public String deleteByid(Integer id) {
        Service str = findById(id);
        if (str == null) {
            return "id não encontrado";
        } else {
            updateServiceStatus(id, "Inativo");
            return "serviço excluido";
        }
    }

    public ServiceResponse updateServiceStatus(Integer id, String status) {
        Service service = findById(id);
        service.setStatus(statusService.findStatusByName(status));
        serviceRepository.save(service);
        return ServiceMapper.toServiceResponse(service);
    }

    // public List<ServiceResponse> findServicesByEstablishmentIdAndStatus(Integer id, String status) {
    //     List<Service> services = serviceRepository.findServicesByEstablishmentIdAndStatus(id, status);
    //     return ServiceMapper.toServiceResponseList(services);
    // }

}

