package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

import lombok.Builder;
@Builder
public record EstablishmentRequest(

        @NotBlank
        String aditumId,

        @NotBlank
        String name,

        String imgUrl,

        @NotNull
        Integer localId,

        @NotNull
        Integer phoneId,

        @NotNull
        Integer statusId,
        
        @NotNull
        LocalTime startShift,

        @NotNull
        LocalTime endShift,

        @NotNull
        String description,

        @NotNull
        String cnpj
) {
}