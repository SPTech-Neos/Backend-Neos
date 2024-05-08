package school.sptech.neosspringjava.api.dtos.serviceDto;

import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.model.filter.Filter;

public record ServiceResponse(Integer id, String specification, ServiceType serviceType, Filter filter) {
    
}
