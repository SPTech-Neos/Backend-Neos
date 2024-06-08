package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.util.List;

import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;

public record EstablishmentFullResponse(
EstablishmentRespose establishmentRespose, 
List<EmployeeResponse> employees,
List<FilterResponse> filters,
List<ProductResponse> products
) {

}
