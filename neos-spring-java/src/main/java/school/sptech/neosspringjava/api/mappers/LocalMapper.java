package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.LocalDto.LocalRequest;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.domain.model.Local;

@Component
public class LocalMapper {

    public static LocalResponse toLocalResponse(Local local) {
        return new LocalResponse(local.getId(), local.getNumber(), local.getFloor(), local.getBlock(), local.getComplement(), local.getAddress());
    }

    public static List<LocalResponse> toLocalResponse(List<Local> local) {
        return local.stream().map(LocalMapper::toLocalResponse).collect(Collectors.toList());
    }
}
