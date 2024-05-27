package school.sptech.neosspringjava.service.addressService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import school.sptech.neosspringjava.api.dtos.addressDto.AddressRequest;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressResponse;
import school.sptech.neosspringjava.api.mappers.addressMapper.AddressMapper;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.repository.adressRepository.AddressRepository;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService;

    @Test
    void testDelete() {
       
        AddressRequest a = AddressRequest.builder()
                .street("Rua 1")
                .city("Cidade 1")
                .state("Estado 1")
                .build();

        
        Address address = new Address();
        address.setId(1);
        address.setStreet("Rua 1");
        address.setCity("Cidade 1");
        address.setState("Estado 1");
        when(addressMapper.toAddress(a)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toAddressResponse(address))
                .thenReturn(new AddressResponse(1, "Rua 1", "Cidade 1", "Estado 1"));

        AddressResponse response = addressService.save(a);

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        when(addressMapper.toAddressResponse(address))
                .thenReturn(new AddressResponse(1, "Rua 1", "Cidade 1", "Estado 1"));

        AddressResponse response2 = addressService.findById(1);

        addressService.delete(1);

        verify(addressRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindById() {
   
        AddressRequest a = AddressRequest.builder()
                .street("Rua 1")
                .city("Cidade 1")
                .state("Estado 1")
                .build();

        
        Address address = new Address();
        address.setId(1);
        address.setStreet("Rua 1");
        address.setCity("Cidade 1");
        address.setState("Estado 1");
        when(addressMapper.toAddress(a)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toAddressResponse(address))
                .thenReturn(new AddressResponse(1, "Rua 1", "Cidade 1", "Estado 1"));

        AddressResponse response = addressService.save(a);

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        when(addressMapper.toAddressResponse(address))
                .thenReturn(new AddressResponse(1, "Rua 1", "Cidade 1", "Estado 1"));

        AddressResponse response2 = addressService.findById(1);

        assertEquals(response, response2);
    }

    @Test
    void testSave() {

        AddressRequest a = AddressRequest.builder()
                .street("Rua 1")
                .city("Cidade 1")
                .state("Estado 1")
                .build();

        
        Address address = new Address();
        address.setId(1);
        address.setStreet("Rua 1");
        address.setCity("Cidade 1");
        address.setState("Estado 1");
        when(addressMapper.toAddress(a)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toAddressResponse(address))
                .thenReturn(new AddressResponse(1, "Rua 1", "Cidade 1", "Estado 1"));

        AddressResponse response = addressService.save(a);

        assertEquals("Rua 1", response.street());
        assertEquals("Cidade 1", response.city());
        assertEquals("Estado 1", response.state());
    }


}
