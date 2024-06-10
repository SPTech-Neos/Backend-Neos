package school.sptech.neosspringjava.service.filterService;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.filter.Filter;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterRequest;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.mappers.filterMapper.FilterMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;
    private final FilterMapper filterMapper;

    public List<FilterResponse> findAllByEstablishment(Establishment establishment) {

        return filterMapper.toFilterResponse(filterRepository.findAllByEstablishment(establishment));
    }

    public List<FilterResponse> findAll() {
        return filterMapper.toFilterResponse(filterRepository.findAll());
    }

    public FilterResponse findById(Integer id) {
        return filterMapper.toFilterResponse(filterRepository.findById(id).get());
    }

  

        

}
