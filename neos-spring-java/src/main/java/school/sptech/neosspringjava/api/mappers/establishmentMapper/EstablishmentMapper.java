package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Component
public class EstablishmentMapper {

    public static EstablishmentResponse toEstablishmentResponse(Establishment establishment) {
        EstablishmentResponse eDto = new EstablishmentResponse();

        eDto.setId(establishment.getId());
        eDto.setName(establishment.getName());
        eDto.setImgUrl(establishment.getImgUrl());
        eDto.setLocal(establishment.getLocal());
        eDto.setStatus(establishment.getStatus());
        eDto.setAditumId(establishment.getAditumId());

        return eDto;
    }

    public static List<EstablishmentResponse> toEstablishmentResponseList(List<Establishment> establishments) {
        return establishments.stream().map(EstablishmentMapper::toEstablishmentResponse).collect(Collectors.toList());
    }

    public static Establishment toEstablishment(EstablishmentResponse establishmentResponse) {
        Establishment e = new Establishment();

        e.setId(establishmentResponse.getId());
        e.setName(establishmentResponse.getName());
        e.setImgUrl(establishmentResponse.getImgUrl());
        e.setLocal(establishmentResponse.getLocal());
        e.setStatus(establishmentResponse.getStatus());
        e.setAditumId(establishmentResponse.getAditumId());

        return e;
    }

    public static List<Establishment> toEstablishment(List<EstablishmentResponse> establishmentResponses) {
        return establishmentResponses.stream()
                .map(EstablishmentMapper::toEstablishment)
                .collect(Collectors.toList());
    }

}
