package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Component
public class EstablishmentMapper {

    public static EstablishmentResponse toEstablishmentResponse(Establishment establishment) {
        EstablishmentResponse eDto = new EstablishmentResponse(
                establishment,
                null
        );
        return eDto;
    }

    public static List<EstablishmentResponse> toEstablishmentResponseList(List<Establishment> establishments) {
        return establishments.stream().map(EstablishmentMapper::toEstablishmentResponse).collect(Collectors.toList());
    }

    public static List<EstablishmentResponse> toEstablishmentResponseList(List<Establishment> establishments, List<Double> medias) {
        List<EstablishmentResponse> eDtos = new ArrayList<>();

        for (int i = 0; i < establishments.size(); i++) {
            Double media = medias.size() > i ? medias.get(i).doubleValue() : 0.0;

            eDtos.add(new EstablishmentResponse(
                    establishments.get(i),
                    media
            ));
        }

        return eDtos;
    }

    public static Establishment toEstablishment(EstablishmentResponse establishmentResponse) {
        Establishment e = new Establishment();

        e.setId(establishmentResponse.id());
        e.setName(establishmentResponse.name());
        e.setImgUrl(establishmentResponse.imgUrl());
        e.setLocal(establishmentResponse.local());
        e.setStatus(establishmentResponse.status());
        e.setAditumId(establishmentResponse.aditumId());

        return e;
    }

    public static List<Establishment> toEstablishment(List<EstablishmentResponse> establishmentResponses) {
        return establishmentResponses.stream()
                .map(EstablishmentMapper::toEstablishment)
                .collect(Collectors.toList());
    }

}
