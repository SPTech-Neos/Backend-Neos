package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.util.List;

import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;

public record EstablishmentFullResponse(
EstablishmentResponse establishmentResponse,
List<EmployeeRelacionamento> employees,
List<FilterResponse> filters,
List<ProductResponse> products
) {

}
