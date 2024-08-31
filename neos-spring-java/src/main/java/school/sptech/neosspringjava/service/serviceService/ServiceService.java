package school.sptech.neosspringjava.service.serviceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper ServiceMapper;
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceResponse save(ServiceRequest serviceRequest) {

        Integer serviceTypeId = serviceRequest.serviceType();
        if (serviceTypeId == null) {
            throw new IllegalArgumentException("O ID do ServiceType não pode ser nulo.");
        }

        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(() -> new IllegalArgumentException("ServiceType não encontrado com o ID: " + serviceTypeId));

        Service service = new Service();
        service.setSpecification(serviceRequest.specification());
        service.setImgUrl(serviceRequest.imgUrl());
        service.setServiceType(serviceType);

        serviceRepository.save(service);

        // Mapeie o serviço para a resposta e retorne
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
        Optional<Service> serviceOp = serviceRepository.findById(id);
        Service service = serviceOp.get();

        service.setSpecification(serviceRequest.specification());
        service.setImgUrl(serviceRequest.imgUrl());
        service.setServiceType(serviceTypeRepository.findById(serviceRequest.serviceType()).get());

        serviceRepository.save(service);
        return ServiceMapper.toServiceResponse(service);
    }

    public ServiceResponse partialUpdate(Integer id, Map<String, Object> updates) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com o ID: " + id));

        // Atualizar os campos especificados nos updates
        if (updates.containsKey("specification")) {
            service.setSpecification((String) updates.get("specification"));
        }

        if (updates.containsKey("imgUrl")) {
            service.setImgUrl((String) updates.get("imgUrl"));
        }

        if (updates.containsKey("serviceType")) {
            Integer serviceTypeId = (Integer) updates.get("serviceType");
            ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId)
                    .orElseThrow(() -> new IllegalArgumentException("ServiceType não encontrado com o ID: " + serviceTypeId));
            service.setServiceType(serviceType);
        }

        // Salvar as atualizações no banco de dados
        Service updatedService = serviceRepository.save(service);

        // Mapear o serviço atualizado para a resposta
        return ServiceMapper.toServiceResponse(updatedService);
    }

    public String deleteByid(Integer id) {
        Service str = findById(id);
        if (str == null) {
            return "id não encontrado";
        } else {
            serviceRepository.deleteById(id);
            return "tipo de serviço excluido";
        }
    }

}

