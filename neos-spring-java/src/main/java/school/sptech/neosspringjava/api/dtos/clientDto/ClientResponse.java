package school.sptech.neosspringjava.api.dtos.clientDto;

import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.model.Phone;

public record ClientResponse(Integer idClient, String name, String email, String password, Local local, Phone phone) {

}
