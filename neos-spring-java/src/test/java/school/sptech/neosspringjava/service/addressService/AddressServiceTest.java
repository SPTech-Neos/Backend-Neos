package school.sptech.neosspringjava.service.addressService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.repository.adressRepository.AddressRepository;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressRequest;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressResponse;
import school.sptech.neosspringjava.api.mappers.addressMapper.AddressMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        AddressRequest addressRequest = new AddressRequest("Public Place", "Street", "City", "State");
        Address address = Address.builder()
                .publicPlace(addressRequest.publicPlace())
                .city(addressRequest.city())
                .build();
        AddressResponse addressResponse = new AddressResponse(0, "Public Place", "Street", "City", "State");

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressResponse result = addressService.save(addressRequest);

        assertEquals(addressResponse, result);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void testFindAll() {
        List<Address> addresses = Arrays.asList(
                new Address(1, "Public Place 1", "Street 1", "City 1", "State 1"),
                new Address(2, "Public Place 2", "Street 2", "City 2", "State 2")
        );
        List<AddressResponse> addressResponses = Arrays.asList(
                new AddressResponse(1, "Public Place 1", "Street 1", "City 1", "State 1"),
                new AddressResponse(2, "Public Place 2", "Street 2", "City 2", "State 2")
        );

        when(addressRepository.findAll()).thenReturn(addresses);

        List<AddressResponse> result = addressService.findAll();

        assertEquals(addressResponses, result);
        verify(addressRepository).findAll();
    }

    @Test
    void testFindById() {
        Address address = new Address(1, "Public Place", "Street", "City", "State");
        AddressResponse addressResponse = new AddressResponse(1, "Public Place", "Street", "City", "State");

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        AddressResponse result = addressService.findById(1);

        assertEquals(addressResponse, result);
        verify(addressRepository).findById(1);
    }

    @Test
    void testUpdate() {
        AddressRequest addressRequest = new AddressRequest("Public Place","Updated Street", "Updated City", "Updated State");
        Address address = new Address(1, "Public Place", "Street", "City", "State");
        Address updatedAddress = new Address(1, "Public Place", "Updated Street", "Updated City", "Updated State");
        AddressResponse addressResponse = new AddressResponse(1, "Public Place", "Updated Street", "Updated City", "Updated State");

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

        AddressResponse result = addressService.update(1, addressRequest);

        assertEquals(addressResponse, result);
        verify(addressRepository).findById(1);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void testDelete() {
        Address address = new Address(1, "Public Place", "Street", "City", "State");

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        addressService.delete(1);

        verify(addressRepository).findById(1);
        verify(addressRepository).delete(address);
    }
}
