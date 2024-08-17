package school.sptech.neosspringjava.service.establishmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstablishmentServiceTest {

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private EstablishmentMapper establishmentMapper;

    @Mock
    private LocalRepository localRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EstablishmentService establishmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        EstablishmentRequest request = new EstablishmentRequest("1", 1, "Test Establishment", 1);
        Local local = new Local();
        Company company = new Company();
        Establishment establishment = new Establishment();
        EstablishmentResponse response ;

        when(localRepository.findById(1)).thenReturn(Optional.of(local));
        when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        when(establishmentRepository.save(any(Establishment.class))).thenReturn(establishment);

        EstablishmentResponse result = establishmentService.save(request);

        verify(localRepository).findById(1);
        verify(companyRepository).findById(1);
        verify(establishmentRepository).save(any(Establishment.class));
        verify(establishmentMapper).toEstablishmentResponse(any(Establishment.class));
    }

    @Test
    void testSave_LocalNotFound() {
        EstablishmentRequest request = new EstablishmentRequest("1", 1, "Test Establishment", 1);

        when(localRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> establishmentService.save(request));

        assertEquals("Local não encontrado", exception.getMessage());
        verify(localRepository).findById(1);
        verify(companyRepository, never()).findById(anyInt());
        verify(establishmentRepository, never()).save(any(Establishment.class));
    }

    @Test
    void testFindById_Success() {
        Establishment establishment = new Establishment();
        
        when(establishmentRepository.findById(1)).thenReturn(Optional.of(establishment));
        when(establishmentMapper.toEstablishmentResponse(any(Establishment.class))).thenReturn(new EstablishmentResponse(1, "Test Establishment", new Company(), "Test Local", new Local()));

        EstablishmentResponse result = establishmentService.findById(1);

        assertNotNull(result);
        verify(establishmentRepository).findById(1);
        verify(establishmentMapper).toEstablishmentResponse(any(Establishment.class));
    }

    @Test
    void testFindById_NotFound() {
        when(establishmentRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> establishmentService.findById(1));

        assertEquals("Estabelecimento não encontrado", exception.getMessage());
        verify(establishmentRepository).findById(1);
        verify(establishmentMapper, never()).toEstablishmentResponse(any(Establishment.class));
    }

    @Test
    void testFindAll() {
        List<Establishment> establishments = new ArrayList<>();
        establishments.add(new Establishment());
        when(establishmentRepository.findAll()).thenReturn(establishments);

        List<EstablishmentResponse> responses = new ArrayList<>();
        when(establishmentMapper.toEstablishmentResponseList(establishments)).thenReturn(responses);

        List<EstablishmentResponse> result = establishmentService.findAll();

        assertNotNull(result);
        verify(establishmentRepository).findAll();
        verify(establishmentMapper).toEstablishmentResponseList(establishments);
    }

    @Test
    void testDelete_Success() {
        Integer id = 1;
        doNothing().when(establishmentRepository).deleteById(id);

        establishmentService.delete(id);

        verify(establishmentRepository).deleteById(id);
    }
}
