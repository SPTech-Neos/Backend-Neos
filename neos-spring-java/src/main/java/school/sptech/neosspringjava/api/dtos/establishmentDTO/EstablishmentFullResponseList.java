package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;

import java.util.List;

public record EstablishmentFullResponseList(List<EstablishmentResponse> establishmentResponses,
                                            List<EmployeeRelacionamento> employees,
                                            List<FilterResponse> filters,
                                            List<ProductResponse> products) {
}
