package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.time.LocalTime;
import java.util.List;

import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;

public record EstablishmentResquest(String name, String cnpj, LocalTime startShift, LocalTime endShift, Local local, String description, List<Filter> filters) {

}