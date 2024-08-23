package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.status.Status;


public record EstablishmentResponse(
        Integer id,
        String name,
        String imgUrl,
        Local local,
        Status status,
        String aditumId

     ) {
}