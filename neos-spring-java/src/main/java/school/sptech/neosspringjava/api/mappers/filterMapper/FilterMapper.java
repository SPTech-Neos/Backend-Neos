package school.sptech.neosspringjava.api.mappers.filterMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterRequest;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;

@Component
public class FilterMapper {

    public FilterResponse  toResponse(Filter filter) {
        return new FilterResponse(filter.getId(), filter.getPrice(), filter.getFkEstablishment(), filter.getFkService());
    }

    public List<FilterResponse> toResponseList(List<Filter> filters) {
        return filters.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Filter toFilter(FilterRequest filterRequest) {
        return Filter.builder().price(filterRequest.price()).fkEstablishment(filterRequest.fkEstablishment()).fkService(filterRequest.fkService()).build();
    }
}
