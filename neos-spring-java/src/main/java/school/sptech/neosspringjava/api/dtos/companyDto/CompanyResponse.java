package school.sptech.neosspringjava.api.dtos.companyDto;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public record CompanyResponse(Integer idCompany, String name, String cnpj) {

}
