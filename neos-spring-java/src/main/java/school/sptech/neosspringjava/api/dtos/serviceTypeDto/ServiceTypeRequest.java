package school.sptech.neosspringjava.api.dtos.serviceTypeDto;

import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;

public record ServiceTypeRequest(Integer id, String name, ServiceCategory ServiceCategory) {

}
