package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.domain.model.local.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EstablishmentRequest(
        String name,
        String cnpj,
        LocalTime startShift,
        LocalTime endShift,
        Local local,
        String profilePic,
        String description,
        List<Integer> fkServices) {
}