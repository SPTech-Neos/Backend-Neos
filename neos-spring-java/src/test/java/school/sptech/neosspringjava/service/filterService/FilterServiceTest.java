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

}
