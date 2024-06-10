package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;

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

    public List<Establishment> toEstablishment(List<EstablishmentRespose> establishmentResposes) {
        return establishmentResposes.stream()
                .map(this::toEstablishment)
                .collect(Collectors.toList());
    }

}
