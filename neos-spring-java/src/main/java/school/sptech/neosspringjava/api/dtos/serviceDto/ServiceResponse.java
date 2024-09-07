package school.sptech.neosspringjava.api.dtos.serviceDto;

import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

public record ServiceResponse(Integer id, String specification, String imgUrl, Double price, ServiceType serviceType) {
    
}
