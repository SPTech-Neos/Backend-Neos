package school.sptech.neosspringjava.api.dtos.ratingDto;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record RatingEmployeeRequest(
        @NotNull
        Integer avaliation,
        @NotNull
        Integer employee,
        @NotNull
        Integer client
) {
}
