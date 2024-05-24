package school.sptech.neosspringjava.api.dtos.serviceDto;

import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

public record ServiceRequest(Integer id, String specification, ServiceType serviceType) {

}
