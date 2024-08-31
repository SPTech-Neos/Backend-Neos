package school.sptech.neosspringjava.api.dtos.addressDto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AddressRequest(
        @NotBlank
        String publicPlace,
        @NotBlank
        String city,
        @Size(max = 2)
        @NotBlank
        String uf) {

}
