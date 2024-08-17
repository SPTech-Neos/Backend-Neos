package school.sptech.neosspringjava.api.dtos.addressDto;


import lombok.Builder;

@Builder
public record AddressRequest(String publicPlace, String city, String uf) {

}
