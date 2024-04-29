package school.sptech.neosspringjava.api.controllers.produtcController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.productMapper.ProductMapper;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productMapper.toResponseList(productRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam Integer id) {
        return ResponseEntity.ok(productMapper.toResponse(productRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(201).body(productMapper.toResponse(productRepository.save(productMapper.toProduct(productRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestParam Integer id, @RequestBody ProductRequest productRequest) {
        Product productToUpdate = productRepository.findById(id).get();
        productToUpdate.setName(productRequest.name());
        productToUpdate.setBrand(productRequest.brand());
        productToUpdate.setFkProductType(productRequest.fkProductType());
        productToUpdate.setFkEstablishment(productRequest.fkEstablishment());
        return ResponseEntity.ok(productMapper.toResponse(productRepository.save(productToUpdate)));
    }

    @DeleteMapping("/{id}")

    public void deleteProduct(@RequestParam Integer id) {
        productRepository.deleteById(id);
    }
    
}
