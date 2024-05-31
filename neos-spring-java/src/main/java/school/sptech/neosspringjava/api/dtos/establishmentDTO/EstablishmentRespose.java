package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

@Data
public class EstablishmentRespose {
    private Integer id;
    private String name;
    private LocalTime startShift;
    private LocalTime endShift;
    private Double assessment;
    private Integer qtdAssessment;
    private Local local;
    private String description;
    private List<ServiceDto> services;
    private List<FilterDto> filters;

    @Data
    public static class FilterDto {
        private Integer id;
        private Double price;
        private Service service;

    }

    @Data
    public static class ServiceDto {
        private Integer id;
        private String specification;
        private ServiceType serviceType;
    }

}