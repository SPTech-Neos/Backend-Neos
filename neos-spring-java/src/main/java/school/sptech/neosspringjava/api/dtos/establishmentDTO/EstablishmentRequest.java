package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EstablishmentRequest(
        String name,
        Integer companyId,
        String imgUrl,
        Integer localId
     ) {
}