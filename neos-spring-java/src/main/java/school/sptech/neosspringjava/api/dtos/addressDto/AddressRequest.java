package school.sptech.neosspringjava.api.dtos.addressDto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressRequest(
        @NotBlank
        String publicPlace,
        @NotBlank
        String city,
        @Max(2)
        @NotBlank
        String uf) {

}
