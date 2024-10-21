package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.domain.model.Service;

@Component
public class ServiceMapper {

    public static ServiceResponse toServiceResponse(Service service) {
        return new ServiceResponse(service.getId(), service.getSpecification(), service.getAditumId(), service.getPrice(), service.getImgUrl(), service.getServiceType(), service.getStatus());
    }

   public static List<ServiceResponse> toServiceResponseList(List<Service> services) {
        return services.stream().map(ServiceMapper::toServiceResponse).collect(Collectors.toList());
    }
    

   
}
