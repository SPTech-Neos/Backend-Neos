package school.sptech.neosspringjava.api.mappers.serviceTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

@Component
public class ServiceTypeMapper {
    public static ServiceTypeResponse toServiceTypeResponse(ServiceType serviceType) {
       return new ServiceTypeResponse(serviceType.getId(), serviceType.getName(), serviceType.getServiceCategory());
    }

    public static List<ServiceTypeResponse> toServiceResponseList(List<ServiceType> serviceTypeList) {
        return serviceTypeList.stream()
                              .map(ServiceTypeMapper::toServiceTypeResponse)
                              .collect(Collectors.toList());
    }
}
