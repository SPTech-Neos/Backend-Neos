package school.sptech.neosspringjava.api.dtos.phoneDto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PhoneRequest(
        @Size(max = 2)
        String countryCode,

        @Size(max = 2)
        String areaCode,

        @Size(max = 9)
        String number

        ) {

}
