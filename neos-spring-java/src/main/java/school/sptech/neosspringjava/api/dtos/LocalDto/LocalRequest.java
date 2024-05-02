package school.sptech.neosspringjava.api.dtos.LocalDto;

import lombok.Builder;

@Builder
public record LocalRequest(int number, int floor, String bloc, String complement, Integer fkAddress) {

}
