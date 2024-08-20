package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Component
public class EstablishmentMapper {

    public EstablishmentResponse toEstablishmentResponse(Establishment establishment) {
        return new EstablishmentResponse(
            establishment.getId(),
            establishment.getName(),
            establishment.getImgUrl(),
            establishment.getLocal(),
            establishment.getStatus(),
            establishment.getAditumId()
        );
    }

    public List<EstablishmentResponse> toEstablishmentResponseList(List<Establishment> establishments) {
        return establishments.stream().map(this::toEstablishmentResponse).collect(Collectors.toList());
    }

    public Establishment toEstablishment(EstablishmentResponse establishmentResponse) {
        return Establishment.builder()
                .id(establishmentResponse.id())
                .name(establishmentResponse.name())
                .imgUrl(establishmentResponse.imgUrl())
                .local(establishmentResponse.local())
                .status(establishmentResponse.status())
                .aditumId(establishmentResponse.aditumId())
                .build()
        ;
    }

    public List<Establishment> toEstablishment(List<EstablishmentResponse> establishmentResponses) {
        return establishmentResponses.stream()
                .map(this::toEstablishment)
                .collect(Collectors.toList());
    }

}
