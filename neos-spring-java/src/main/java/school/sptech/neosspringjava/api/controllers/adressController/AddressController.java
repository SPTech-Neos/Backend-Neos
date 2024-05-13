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
import school.sptech.neosspringjava.service.AddressService;



@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> save(@Valid @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.save(addressRequest));
    }

    @GetMapping("/{id}")

    public ResponseEntity<AddressResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Integer id, @Valid @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.update(id, addressRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    
}
