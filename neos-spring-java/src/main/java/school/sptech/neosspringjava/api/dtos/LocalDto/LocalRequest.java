package school.sptech.neosspringjava.api.dtos.LocalDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.address.Address;

@Builder
public record LocalRequest(int number, int floor, String block, String complement, Integer address) {

}
