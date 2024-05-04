package school.sptech.neosspringjava.api.controllers.adressController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressRequest;
import school.sptech.neosspringjava.api.dtos.addressDto.AddressResponse;
import school.sptech.neosspringjava.api.mappers.addressMapper.AddressMapper;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.repository.adressRepository.AdressRepository;

/**
 * @autor: @GabrielYukioMC
 * @data criação: 2024-03-31
 * @versão: 1.0.0
 * @descrição: Criação do controller EmpresaController
 * @/alterado por: @GabrielYukioMC
 * @data alteração: 2024-03-31
 * 
 **/

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AdressRepository adressRepository;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAdress() {
       
        List<Address> address = adressRepository.findAll();

        return ResponseEntity.ok().body(addressMapper.toAddressResponse(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAdressById(@PathVariable int id) {
        Address address = adressRepository.findById(id).orElseThrow();
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addressMapper.toAddressResponse(address));
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAdress(@Valid @RequestBody AddressRequest addressRequest) {
        Address address = addressMapper.toAddress(addressRequest);
        adressRepository.save(address);
        return ResponseEntity.ok(addressMapper.toAddressResponse(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAdress(@PathVariable int id, @Valid @RequestBody AddressRequest addressRequest) {
        Address addressUpdate = adressRepository.findById(id).orElseThrow();
        if (addressUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        addressUpdate.setStreet(addressRequest.street());
        addressUpdate.setCity(addressRequest.city());
        addressUpdate.setState(addressRequest.state());
        adressRepository.save(addressUpdate);
        return ResponseEntity.ok(addressMapper.toAddressResponse(addressUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdress(@PathVariable int id) {
        Address address = adressRepository.findById(id).orElseThrow();
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        adressRepository.delete(address);
        return ResponseEntity.ok().build();
    }

    
}
