package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.service.Service;

@Data
public class EstablishmentRespose {
    private Integer id;
    private String name;
    private String cnpj;
    private LocalTime startShift;
    private LocalTime endShift;
    private Local local;
    private String description;
    private List<FilterDto> filters;

    @Data
    public static class FilterDto {
        private Integer id;
        private Double price;
        private Service service;

    }

}