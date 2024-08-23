package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Builder;
@Builder
public record EstablishmentRequest(

        @NotBlank
        String name,
        String imgUrl,
        @NotNull
        Integer localId,
        @NotNull
        Integer statusId,
        @NotBlank
        String aditumId
     ) {
}