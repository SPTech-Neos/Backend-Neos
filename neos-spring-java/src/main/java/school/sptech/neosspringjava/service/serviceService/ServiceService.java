package school.sptech.neosspringjava.service.serviceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.service.statusService.StatusService;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper ServiceMapper;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusService statusService; 
    private final EstablishmentRepository establishmentRepository;
    private final EmployeeServicesRepository employeeServicesRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

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

    public ServiceResponse statusUpdate(Integer id, Integer statusId) {
        Service service = findById(id);

        if (statusId != null) {
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
            throw new IllegalArgumentException("service não encontrado com o ID: " + id);
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

    public List<ServiceResponse> findServicesByEstablishmentAndStatus(Integer id, String status) {
                Establishment e = establishmentRepository.findById(id).get();
        if (e == null) {
            throw new IllegalArgumentException("estabelecimento não encontrado com o ID: " + id);
        }
        Status s = statusService.findStatusByName(status);
        if (s == null) {
            throw new IllegalArgumentException("status não encontrado com o ID: " + status);
        }
        List<Service> services = employeeServicesRepository.findServicesByEstablishmentAndStatus(e.getId(), s.getStatusId());
        return ServiceMapper.toServiceResponseList(services);
    }
    public List<ServiceResponse> findServicesByEstablishmentAndStatus(Integer id, Integer status) {
                Establishment e = establishmentRepository.findById(id).get();
        if (e == null) {
            throw new IllegalArgumentException("estabelecimento não encontrado com o ID: " + id);
        }
        Status s = statusService.findById(status);
        if (s == null) {
            throw new IllegalArgumentException("status não encontrado com o ID: " + status);
        }
        List<Service> services = employeeServicesRepository.findServicesByEstablishmentAndStatus(e.getId(), s.getStatusId());
        return ServiceMapper.toServiceResponseList(services);
    }
    
    public List<ServiceResponse> findServicesByEmployeeId(Integer employeeId){
        Employee e = employeeRepository.findById(employeeId).get();
        if (e == null) {
            throw new IllegalArgumentException("funcionario não encontrado com o ID: " + employeeId);
        }
        List<Service> services = employeeServicesRepository.findServicesByEmployeeId(e.getId());
        return ServiceMapper.toServiceResponseList(services);
        
    }
    
    public List<ServiceResponse> findServicesByEmployeeIdAndServiceCategory(Integer employeeId, Integer servCategoryId){
        Employee e = employeeRepository.findById(employeeId).get();
        if (e == null) {
            throw new IllegalArgumentException("funcionario não encontrado com o ID: " + employeeId);
        }
        ServiceCategory sC = serviceCategoryRepository.findById(servCategoryId).get();
        if (sC == null) {
            throw new IllegalArgumentException("Categoria de serviço não encontrado com o ID: " + servCategoryId);
        }
        List<Service> services = employeeServicesRepository.findServicesByEmployeeIdAndServiceCategory(e.getId(), sC.getId());
        return ServiceMapper.toServiceResponseList(services);
        
    }
}

