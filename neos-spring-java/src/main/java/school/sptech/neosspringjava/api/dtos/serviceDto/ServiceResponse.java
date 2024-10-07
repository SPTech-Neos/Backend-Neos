package school.sptech.neosspringjava.api.dtos.serviceDto;

import school.sptech.neosspringjava.domain.model.ServiceType;
import school.sptech.neosspringjava.domain.model.Status;

public record ServiceResponse(
    Integer id,
    String specification,
    String aditumId,
    Double price,
    String imgUrl,
    ServiceType serviceType,
    Status status
) {
}
