package school.sptech.neosspringjava.service.companyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.api.dtos.companyDto.CompanyRequest;
import school.sptech.neosspringjava.api.dtos.companyDto.CompanyResponse;
import school.sptech.neosspringjava.api.mappers.companyMapper.CompanyMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CompanyRequest companyRequest = new CompanyRequest("Company Name", "12345678901234");
        Company company = Company.builder()
                .name(companyRequest.name())
                .cnpj(companyRequest.cnpj())
                .build();
        CompanyResponse companyResponse = new CompanyResponse(0, "Company Name", "12345678901234");

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyResponse result = companyService.save(companyRequest);

        assertEquals(companyResponse, result);
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void testFindById() {
        Company company = new Company(1, "Company Name", "12345678901234");
        CompanyResponse companyResponse = new CompanyResponse(1, "Company Name", "12345678901234");

        when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        CompanyResponse result = companyService.findById(1);

        assertEquals(companyResponse, result);
        verify(companyRepository).findById(1);
    }

    @Test
    void testUpdate() {
        CompanyRequest companyRequest = new CompanyRequest("Updated Company Name", "98765432109876");
        Company company = new Company(1, "Company Name", "12345678901234");
        Company updatedCompany = new Company(1, "Updated Company Name", "98765432109876");
        CompanyResponse companyResponse = new CompanyResponse(1, "Updated Company Name", "98765432109876");

        when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        CompanyResponse result = companyService.update(1, companyRequest);

        assertEquals(companyResponse, result);
        verify(companyRepository).findById(1);
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void testDelete() {
        Company company = new Company(1, "Company Name", "12345678901234");

        when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        companyService.delete(1);

        verify(companyRepository).findById(1);
        verify(companyRepository).delete(company);
    }

    @Test
    void testFindAll() {
        List<Company> companies = Arrays.asList(
                new Company(1, "Company Name 1", "12345678901234"),
                new Company(2, "Company Name 2", "98765432109876")
        );
        List<CompanyResponse> companyResponses = Arrays.asList(
                new CompanyResponse(1, "Company Name 1", "12345678901234"),
                new CompanyResponse(2, "Company Name 2", "98765432109876")
        );

        when(companyRepository.findAll()).thenReturn(companies);

        List<CompanyResponse> result = companyService.findAll();

        assertEquals(companyResponses, result);
        verify(companyRepository).findAll();
    }
}
