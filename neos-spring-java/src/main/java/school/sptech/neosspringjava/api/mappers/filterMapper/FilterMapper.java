package school.sptech.neosspringjava.api.mappers.filterMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterRequest;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;

@Component
public class FilterMapper {

    public static FilterResponse toFilterResponse(Filter filter) {
        return new FilterResponse(filter.getId(), filter.getPrice(), filter.getEstablishment(), filter.getService());
    }

    public static List<FilterResponse> toFilterResponse(List<Filter> filter) {
        return filter.stream().map(FilterMapper::toFilterResponse).collect(Collectors.toList());
    }
}
