package school.sptech.neosspringjava.api.controllers.ProductTypeController;

import org.springframework.beans.factory.annotation.Autowired;
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
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeRequest;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeResponse;
import school.sptech.neosspringjava.api.mappers.productTypeMapper.ProductTypeMapper;
import school.sptech.neosspringjava.domain.model.productType.ProductType;
import school.sptech.neosspringjava.domain.repository.productTypeRepository.ProductTypeRepository;

import java.util.List;

@RestController
@RequestMapping("/ProductType")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeRepository productTypeRopository;
    private final ProductTypeMapper productTypeMapper;

    @GetMapping
    public ResponseEntity<List<ProductTypeResponse>> getProductTypes() {
        return ResponseEntity.ok(productTypeMapper.toResponseList(productTypeRopository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> getProductTypeById(@RequestParam Integer id) {
        return ResponseEntity.ok(productTypeMapper.toResponse(productTypeRopository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<ProductTypeResponse> createProductType(@RequestBody ProductTypeRequest productTypeRequest) {
        return ResponseEntity.status(201).body(productTypeMapper.toResponse(productTypeRopository.save(productTypeMapper.toProductType(productTypeRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> updateProductType(@RequestParam Integer id, @RequestBody ProductTypeRequest productTypeRequest) {
        ProductType productTypeToUpdate = productTypeRopository.findById(id).get();
        productTypeToUpdate.setName(productTypeRequest.name());
        productTypeToUpdate.setSpecification(productTypeRequest.specification());
        return ResponseEntity.ok(productTypeMapper.toResponse(productTypeRopository.save(productTypeToUpdate)));
    }

    @DeleteMapping("/{id}")

    public void deleteProductType(@RequestParam Integer id) {
        productTypeRopository.deleteById(id);
    }
}
