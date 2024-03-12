package school.sptech.neosspringjava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.neosspringjava.services.Service;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private List<Service> services = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<Service>> getAllServices() {
        if (services.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(services);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable int id) {
        if (id >= 0 && id < services.size()) {
            Service service = services.get(id);
            return ResponseEntity.status(200).body(service);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        services.add(service);
        return ResponseEntity.status(200).body(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable int id, @RequestBody Service updatedService) {
        if (id >= 0 && id < services.size()) {
            services.set(id, updatedService);
            return ResponseEntity.status(200).body(updatedService);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> deleteService(@PathVariable int id) {
        if (id >= 0 && id < services.size()) {
            Service deletedService = services.remove(id);
            return ResponseEntity.status(200).body(deletedService);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}