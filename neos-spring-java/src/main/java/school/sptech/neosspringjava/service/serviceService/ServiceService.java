package school.sptech.neosspringjava.service.serviceService;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.exception.NaoEncontradoException;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final ServiceTypeRepository serviceTypeRepository;

    @Transactional
    public ServiceResponse save(ServiceRequest serviceRequest) {
        try {
            Service service = new Service();
            service.setSpecification(serviceRequest.specification());
            service.setServiceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).orElseThrow());

            return serviceMapper.toServiceResponse(serviceRepository.save(service));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o serviço: " + e.getMessage());
        }
    }

    public List<ServiceResponse> findAll() {
        List<Service> service = serviceRepository.findAll();
        return ServiceMapper.toServiceResponseList(service);
    }

    public ServiceResponse findById(Integer id) {
        Optional<Service> serviceOp = serviceRepository.findById(id);
        if (serviceOp.isPresent()) {
            return ServiceMapper.toServiceResponse(serviceOp.get());
        } else {
            throw new NaoEncontradoException("Service");
        }
    }

    public ServiceResponse update(ServiceRequest serviceRequest, Integer id) {
        Optional<Service> existingService = serviceRepository.findById(id);
        if (existingService.isPresent()) {
            Service service = serviceRepository.findById(id).orElseThrow();

            service.setSpecification(serviceRequest.specification());
            if (serviceRequest.fkServiceType() != null) {
                service.setServiceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).orElseThrow(() ->
                        new NaoEncontradoException("ServiceType not found with id " + serviceRequest.fkServiceType())));
            }

            return serviceMapper.toServiceResponse(serviceRepository.save(service));
        } else {
            throw new NaoEncontradoException("Service not found with id " + id);
        }

    }

    public String deleteByid(Integer id) {
        Optional<Service> serviceOp = serviceRepository.findById(id);
        if (serviceOp.isPresent()) {
            serviceRepository.deleteById(id);
            return "tipo de serviço excluído";
        } else {
            return "id não encontrado";
        }
    }

}

