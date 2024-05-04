package school.sptech.neosspringjava.api.dtos.FilterDto;

import lombok.Builder;

@Builder
public record FilterRequest(Double price, Integer fkEstablishment, Integer fkService) {

}
