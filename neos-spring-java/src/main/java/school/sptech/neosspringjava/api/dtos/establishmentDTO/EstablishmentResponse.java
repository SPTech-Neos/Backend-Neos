package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.time.LocalTime;

public record EstablishmentResponse (
      Integer id,
      String aditumId,
      String name,
      String imgUrl,
      Local local,
      Phone phone,
      Status status,
      LocalTime startShift,
      LocalTime endShift,
      String description,
      String cnpj,
      Double media
) {
    public EstablishmentResponse(Establishment e, Double media) {
        this(
            e.getId(),
            e.getAditumId(),
            e.getName(),
            e.getImgUrl(),
            e.getLocal(),
            e.getPhone(),
            e.getStatus(),
            e.getStartShift(),
            e.getEndShift(),
            e.getDescription(),
            e.getCnpj(),
            media
        );
    }
}