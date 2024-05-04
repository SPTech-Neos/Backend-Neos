package school.sptech.neosspringjava.api.dtos.FilterDto;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.service.Service;

public record FilterResponse(Integer id, Double price, Establishment establishment, Service service) {

}
