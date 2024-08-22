package school.sptech.neosspringjava.api.dtos.addressDto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressRequest(
        String publicPlace,
        String city,
        String uf) {

}
