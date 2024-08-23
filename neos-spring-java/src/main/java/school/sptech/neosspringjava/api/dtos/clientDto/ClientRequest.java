package school.sptech.neosspringjava.api.dtos.clientDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClientRequest(

        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String password,
        String imgUrl,
        @NotNull
        Integer local) {

}
