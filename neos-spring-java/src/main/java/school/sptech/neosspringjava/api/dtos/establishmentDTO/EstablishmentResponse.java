package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.model.Phone;
import school.sptech.neosspringjava.domain.model.Status;

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