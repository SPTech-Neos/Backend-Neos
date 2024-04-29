package school.sptech.neosspringjava.api.dtos.productTypeDto;

import lombok.Builder;

@Builder
public record ProductTypeRequest(String name, String specification) {

}
