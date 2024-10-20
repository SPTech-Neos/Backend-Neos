package school.sptech.neosspringjava.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.service.ProductService;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController { 

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        List<ProductResponse> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse createdProduct = productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.update(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> partialUpdateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.partialUpdate(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse product = productService.findById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    @GetMapping("/establishment")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentId(@RequestParam Integer id) {
        List<ProductResponse> products = productService.findProductsByEstablishmentId(id);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ProductResponse> updateProductStatus(@PathVariable Integer id, @RequestParam Integer status) {
        ProductResponse updatedProduct = productService.updateProductStatus(id, status);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/by-establishment/status/name")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentIdAndStatus(@RequestParam Integer id, @RequestParam String status) {
        List<ProductResponse> products = productService.findProductsByEstablishmentIdAndStatus(id, status);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-establishment/status")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentIdAndStatus(@RequestParam Integer id, @RequestParam Integer status) {
        List<ProductResponse> products = productService.findProductsByEstablishmentIdAndStatus(id, status);
        return ResponseEntity.ok(products);
    }
}
