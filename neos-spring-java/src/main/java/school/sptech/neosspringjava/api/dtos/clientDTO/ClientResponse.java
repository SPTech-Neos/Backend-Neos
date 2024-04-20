package school.sptech.neosspringjava.api.dtos.clientDTO;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import school.sptech.neosspringjava.domain.model.local.Local;

public record ClientResponse(Integer idClient, String name, String email, String password, Local local) {

}
