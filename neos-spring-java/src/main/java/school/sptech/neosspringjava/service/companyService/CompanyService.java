package school.sptech.neosspringjava.service.companyService;

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
        Company company = companyRepository.findById(id).orElseThrow();
        return companyMapper.toCompanyResponse(company);
    }

    public CompanyResponse update(Integer id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setName(companyRequest.name());
        company.setCnpj(companyRequest.cnpj());
        companyRepository.save(company);
        return companyMapper.toCompanyResponse(company);
    }

    public void delete(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow();
        companyRepository.delete(company);
    }

}
