package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import lombok.Data;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.status.Status;

public record EstablishmentResponse (
      Integer id,
      String name,
      String imgUrl,
      Local local,
      Status status,
      String aditumId,
      Double media
) {
    public EstablishmentResponse(Establishment e, Double media) {
        this(
                e.getId(),
                e.getName(),
                e.getImgUrl(),
                e.getLocal(),
                e.getStatus(),
                e.getAditumId(),
                media
        );
    }
}