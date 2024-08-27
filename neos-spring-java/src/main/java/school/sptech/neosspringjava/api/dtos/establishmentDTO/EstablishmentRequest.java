package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        String aditumId,
        @NotBlank
        Integer phoneId
     ) {
}