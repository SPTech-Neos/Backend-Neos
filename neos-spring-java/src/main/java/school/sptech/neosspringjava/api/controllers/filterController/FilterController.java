package school.sptech.neosspringjava.api.controllers.filterController;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterRequest;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.mappers.filterMapper.FilterMapper;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;

@RestController
@RequestMapping("/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterMapper filterMapper;
    private final FilterRepository filterRepository;

    @GetMapping
    public ResponseEntity<List<FilterResponse>> getFilter() {
        return ResponseEntity.ok(filterMapper.toResponseList(filterRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilterResponse> getFilterById(@RequestParam Integer id) {
        return ResponseEntity.ok(filterMapper.toResponse(filterRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<FilterResponse> createFilter(@RequestBody FilterRequest filterRequest) {
        return ResponseEntity.status(201).body(filterMapper.toResponse(filterRepository.save(filterMapper.toFilter(filterRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilterResponse> updateFilter(@RequestParam Integer id, @RequestBody FilterRequest filterRequest) {
        Filter filter = filterRepository.findById(id).get();
        filter.setValue(filterRequest.value());
        filter.setFkEstablishment(filterRequest.fkEstablishment());
        filter.setFkService(filterRequest.fkService());
        return ResponseEntity.ok(filterMapper.toResponse(filterRepository.save(filter)));
    }

    @DeleteMapping("/{id}")
    public void deleteFilter(@RequestParam Integer id) {
        filterRepository.deleteById(id);
    }
}
