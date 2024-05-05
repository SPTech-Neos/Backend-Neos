package school.sptech.neosspringjava.api.mappers.establishmentMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Component
public class EstablishmentMapper {

    public static EstablishmentRespose toEstablishmentResponse(Establishment establishment) {
        return new EstablishmentRespose(establishment.getIdEstablishment(),establishment.getName(), establishment.getLocal());
    }

    public List<EstablishmentRespose> toEstablishmentResponse(List<Establishment> establishment) {
        return establishment.stream().map(EstablishmentMapper::toEstablishmentResponse).collect(Collectors.toList());
    }

}
