package school.sptech.neosspringjava.api.mappers.serviceMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.domain.model.service.Service;

@Component
public class ServiceMapper {

    public ServiceResponse toServiceResponse(Service service) {
        return new ServiceResponse(service.getId(), service.getSpecification(), service.getServiceType(), service.getFilter());
    }

   public List<ServiceResponse> toServiceResponseList(List<Service> services) {
        return services.stream().map(this::toServiceResponse).collect(Collectors.toList());
    }

   
}
