package school.sptech.neosspringjava.api.controllers.produtcController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.service.productService.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController { 

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.save(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(id, productRequest));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> partialUpdateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.partialUpdate(id, productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentId(@PathVariable Integer id) {
        // Retorna a lista de serviços convertida para ServiceResponse
        return ResponseEntity.ok().body(productService.findProductsByEstablishmentId(id));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<ProductResponse> updateProductStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return ResponseEntity.ok(productService.updateProductStatus(id, status));
    }

    @GetMapping("/by-establishment/{id}/status/name/{status}")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentIdAndStatus(@PathVariable Integer id, @PathVariable String status) {
        return ResponseEntity.ok(productService.findProductsByEstablishmentIdAndStatus(id, status));
    }
    
    @GetMapping("/by-establishment/{id}/status/{status}")
    public ResponseEntity<List<ProductResponse>> findProductsByEstablishmentIdAndStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return ResponseEntity.ok(productService.findProductsByEstablishmentIdAndStatus(id, status));
    }
}
