package school.sptech.neosspringjava.api.dtos.LocalDto;

import school.sptech.neosspringjava.domain.model.address.Address;

public record LocalResponse(Integer id,int number, int floor, String block, String complement, Address address) {

}
