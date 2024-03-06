package school.sptech.neosspringjava;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.neosspringjava.Address;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private List<Address> addresses = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddresses() {
        if (addresses.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(addresses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            Address address = addresses.get(id);
            return ResponseEntity.status(200).body(address);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address show = null;
        int finish = 0;
        ;

        for (int i = 0; i < addresses.size(); i++) {
            show = addresses.get(i);
            if (addresses.get(i).equals(address)) {
                finish++;
                break;
            }
        }

        if (finish > 0) {
            return ResponseEntity.status(200).body(show);
        } else {
            addresses.add(address);
            return ResponseEntity.status(201).body(address);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address updatedAddress) {
        if (id >= 0 && id < addresses.size()) {
            addresses.set(id, updatedAddress);
            return ResponseEntity.status(200).body(updatedAddress);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            Address deletedAddress = addresses.remove(id);
            return ResponseEntity.status(200).body(deletedAddress);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}