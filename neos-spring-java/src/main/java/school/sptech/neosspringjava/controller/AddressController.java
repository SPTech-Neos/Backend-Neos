package school.sptech.neosspringjava.controller;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private List<Address> addresses = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.status(200).body(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            return ResponseEntity.status(200).body(addresses.get(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        addresses.add(address);
        return ResponseEntity.status(201).body(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address updatedAddress) {
        if (id >= 0 && id < addresses.size()) {
            addresses.set(id, updatedAddress);
            return ResponseEntity.status(200).body(updatedAddress);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            addresses.remove(id);
            return ResponseEntity.status(200).body(addresses.get(id));
        }
        return ResponseEntity.status(404).build();
    }
}