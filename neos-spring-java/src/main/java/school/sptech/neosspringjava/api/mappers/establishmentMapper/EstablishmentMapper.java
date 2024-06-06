package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.service.Service;

@Component
public class EstablishmentMapper {

    public EstablishmentRespose toEstablishmentResponse(Establishment establishment) {
        return new EstablishmentRespose(
            establishment.getId(),
            establishment.getName(),
            establishment.getCompany(),
            establishment.getImgUrl(),
            establishment.getLocal() 
        );

    }

    private static List<EstablishmentRespose.ServiceDto> toServiceDto(final List<Service> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }



    public List<EstablishmentRespose> toEstablishmentResponseList(List<Establishment> establishments) {
        return establishments.stream().map(this::toEstablishmentResponse).collect(Collectors.toList());
    }

    public Establishment toEstablishment(EstablishmentRespose establishmentRespose) {
        return Establishment.builder()
            .id(establishmentRespose.id())
            .name(establishmentRespose.name())
            .company(establishmentRespose.company())
            .imgUrl(establishmentRespose.imgUrl())
            .local(establishmentRespose.local())
            .build();
    }
}
