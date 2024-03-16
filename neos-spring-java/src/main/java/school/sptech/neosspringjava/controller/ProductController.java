package school.sptech.neosspringjava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.neosspringjava.modal.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        if (products.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(products);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        if (id >= 0 && id < products.size()) {
            Product product = products.get(id);
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(200).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        if (id >= 0 && id < products.size()) {
            products.set(id, updatedProduct);
            return ResponseEntity.status(200).body(updatedProduct);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        if (id >= 0 && id < products.size()) {
            Product deletedProduct = products.remove(id);
            return ResponseEntity.status(200).body(deletedProduct);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}