package school.sptech.neosspringjava.service.filterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.mappers.filterMapper.FilterMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilterServiceTest {

    @Mock
    private FilterRepository filterRepository;

    @Mock
    private FilterMapper filterMapper;

    @InjectMocks
    private FilterService filterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllByEstablishment_Success() {
        Establishment establishment = new Establishment();
        List<FilterResponse> filterResponses = new ArrayList<>();
        List<Filter> filters = new ArrayList<>();

        when(filterRepository.findAllByEstablishment(establishment)).thenReturn(filters);

        List<FilterResponse> result = filterService.findAllByEstablishment(establishment);

        assertEquals(filterResponses, result);
        verify(filterRepository).findAllByEstablishment(establishment);
        verify(filterMapper).toFilterResponse(filters);
    }

    @Test
    void testFindAll_Success() {
        List<FilterResponse> filterResponses = new ArrayList<>();
        List<Filter> filters = new ArrayList<>();

        when(filterRepository.findAll()).thenReturn(filters);

        List<FilterResponse> result = filterService.findAll();

        assertNotNull(result);
        assertEquals(filterResponses, result);
        verify(filterRepository).findAll();
        verify(filterMapper).toFilterResponse(filters);
    }

    @Test
    void testFindById_Success() {
        Integer id = 1;
        Filter filter = new Filter();
        FilterResponse filterResponse = new FilterResponse(null, null, null, null);

        when(filterRepository.findById(id)).thenReturn(Optional.of(filter));
        FilterResponse result = filterService.findById(id);

        assertNotNull(result);
        assertEquals(filterResponse, result);
        verify(filterRepository).findById(id);
        verify(filterMapper).toFilterResponse(filter);
    }

    @Test
    void testFindById_NotFound() {
        Integer id = 1;

        when(filterRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> filterService.findById(id));

        verify(filterRepository).findById(id);
    }

  
}
