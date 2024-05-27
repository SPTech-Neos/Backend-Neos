package school.sptech.neosspringjava.api.dtos.addressDto;

import lombok.Builder;

@Builder
public record AddressResponse(Integer id, String street, String city, String state) {



}
