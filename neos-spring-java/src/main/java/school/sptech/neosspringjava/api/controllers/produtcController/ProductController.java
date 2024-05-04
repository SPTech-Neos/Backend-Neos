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
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.productType.ProductType;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.productTypeRepository.ProductTypeRepository;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;
    private final EstablishmentRopository establishmentRopository;


    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productMapper.toProductResponse(productRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(productMapper.toProductResponse(productRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .brand(productRequest.brand())
                .productType(productTypeRepository.findById(productRequest.fkProductType()).get())
                .establishment(establishmentRopository.findById(productRequest.fkEstablishment()).get())
                .build();
        return ResponseEntity.ok(productMapper.toProductResponse(productRepository.save(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestParam Integer id, @RequestBody ProductRequest productRequest) {
        Product product = Product.builder()
                .id(id)
                .name(productRequest.name())
                .brand(productRequest.brand())
                .productType(productTypeRepository.findById(productRequest.fkProductType()).get())
                .establishment(establishmentRopository.findById(productRequest.fkEstablishment()).get())
                .build();
        return ResponseEntity.ok(productMapper.toProductResponse(productRepository.save(product)));
    }


    @DeleteMapping("/{id}")

    public void deleteProduct(@RequestParam Integer id) {
        productRepository.deleteById(id);
    }
    
}
