package school.sptech.neosspringjava.service.addressService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.repository.adressRepository.AddressRepository;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.api.dtos.addressDto.*;
import school.sptech.neosspringjava.api.mappers.addressMapper.AddressMapper;;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressResponse save(AddressRequest addressRequest) {
        Address address = addressMapper.toAddress(addressRequest);
        addressRepository.save(address);
        System.out.println(address);
        System.out.println( addressMapper.toAddressResponse(address));
        return addressMapper.toAddressResponse(address);
    }

    public List<AddressResponse> findAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> addressResponseList = new ArrayList<>();
        for (Address address : addresses) {
            addressResponseList.add(addressMapper.toAddressResponse(address));
        }
        return addressResponseList;
        
    }
    public AddressResponse findById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            return addressMapper.toAddressResponse(address.get());
        } else {
            return null;
        }
    }

    public AddressResponse update(Integer id, AddressRequest addressRequest) {
        Address address = addressRepository.findById(id).orElseThrow();
        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    public void delete(Integer id) {
        
        addressRepository.deleteById(id);
    }

    

}
