package school.sptech.neosspringjava.api.mappers.companyMapper;

import java.util.List;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.companyDto.CompanyRequest;
import school.sptech.neosspringjava.api.dtos.companyDto.CompanyResponse;
import school.sptech.neosspringjava.domain.model.company.Company;

@Component
public class CompanyMapper {

    public static CompanyResponse toCompanyResponse(Company company) {
        return new CompanyResponse(company.getCompanyId(), company.getName(), company.getCnpj());
    }

    public static List<CompanyResponse> toCompanyResponseList(List<Company> company) {
        return company.stream().map(CompanyMapper::toCompanyResponse).toList();
    }

   
}
