package school.sptech.neosspringjava;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private List<Address> addresses = new ArrayList<>();

    @GetMapping("/")
    public List<Address> getAllAddresses() {
        return addresses;
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            return addresses.get(id);
        }
        return null;
    }

    @PostMapping("/")
    public Address createAddress(@RequestBody Address address) {
        addresses.add(address);
        return address;
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable int id, @RequestBody Address updatedAddress) {
        if (id >= 0 && id < addresses.size()) {
            addresses.set(id, updatedAddress);
            return updatedAddress;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable int id) {
        if (id >= 0 && id < addresses.size()) {
            return addresses.remove(id);
        }
        return null;
    }
}