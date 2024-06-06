package school.sptech.neosspringjava.service.companyService;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.api.dtos.companyDto.*;
import school.sptech.neosspringjava.api.mappers.companyMapper.CompanyMapper;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = Company.builder()
                .name(companyRequest.name())
                .cnpj(companyRequest.cnpj())
                .build();
        companyRepository.save(company);
        return companyMapper.toCompanyResponse(company);
    }

    public CompanyResponse findById(Integer id) {
       
        try {
        Company company = companyRepository.findById(id).orElseThrow();

            return companyMapper.toCompanyResponse(company);
        } catch (Exception e) {
            return null;
        }
    }

   public CompanyResponse update(Integer id, CompanyRequest companyRequest) {
        try {
            Company company = companyRepository.findById(id).orElseThrow();
        company.setName(companyRequest.name());
        company.setCnpj(companyRequest.cnpj());
        companyRepository.save(company);
        return companyMapper.toCompanyResponse(company);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow();
        companyRepository.delete(company);
    }

    public List<CompanyResponse> findAll() {
        return companyMapper.toCompanyResponseList(companyRepository.findAll());
    }
}
