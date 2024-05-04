package school.sptech.neosspringjava.api.mappers.addressMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.addressDto.AddressRequest;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressResponse;
import school.sptech.neosspringjava.domain.model.address.Address;

@Component
public class AddressMapper {

    public static AddressResponse toAddressResponse(Address address) {
        return new AddressResponse(address.getIdAddress(), address.getStreet(), address.getCity(), address.getState());
    }

    public static List<AddressResponse> toAddressResponse(List<Address> addresses) {
        return addresses.stream().map(AddressMapper::toAddressResponse).collect(Collectors.toList());
    }

    public static Address toAddress(AddressRequest addressRequest) {

        return Address.builder()
                .street(addressRequest.street())
                .city(addressRequest.city())
                .state(addressRequest.state())
                .build();
    }
}
