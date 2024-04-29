package school.sptech.neosspringjava.api.mappers.localMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.LocalDto.LocalRequest;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.domain.model.local.Local;

@Component
public class LocalMapper {

    public LocalResponse toResponse(Local local) {
        return new LocalResponse(local.getIdLocal(), local.getNumber(), local.getFloor(), local.getBloc(), local.getComplement(), local.getFkAddress());
    }

    public List<LocalResponse> toResponseList(List<Local> locals) {
        return locals.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Local toLocal(LocalRequest localRequest) {
        return Local.builder().number(localRequest.number()).floor(localRequest.floor()).bloc(localRequest.bloc()).complement(localRequest.complement()).fkAddress(localRequest.fkAddress()).build();
    }
}
