package school.sptech.neosspringjava.api.mappers.addressMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.addressDto.AddressRequest;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressResponse;
import school.sptech.neosspringjava.domain.model.address.Address;

@Component
public class AddressMapper {

    public AddressResponse toAddressResponse(Address address) {
        return new AddressResponse(address.getId(), address.getStreet(), address.getCity(), address.getState());
    }

    public List<AddressResponse> toAddressResponse(List<Address> addresses) {
        return addresses.stream().map(this::toAddressResponse).collect(Collectors.toList());
    }

    public Address toAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        return address;
    }
}