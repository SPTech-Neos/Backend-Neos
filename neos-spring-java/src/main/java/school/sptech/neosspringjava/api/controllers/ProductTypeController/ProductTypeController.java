package school.sptech.neosspringjava.api.controllers.ProductTypeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeRequest;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeResponse;
import school.sptech.neosspringjava.service.productTypeService.ProductTypeService;

import java.util.List;

@RestController
@RequestMapping("/product-types")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductTypeResponse>> getProductTypes() {
        return ResponseEntity.ok(productTypeService.findAll());
       
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> getProductTypeById(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok(productTypeService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
      
    }

    @PostMapping
    public ResponseEntity<ProductTypeResponse> createProductType(@RequestBody ProductTypeRequest productTypeRequest) {
        return ResponseEntity.ok(productTypeService.save(productTypeRequest));
       
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> updateProductType(@PathVariable Integer id, @RequestBody ProductTypeRequest productTypeRequest) {
        try {
            productTypeService.update(productTypeRequest, id);
            return ResponseEntity.ok(productTypeService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteProductType(@PathVariable Integer id) {
        productTypeService.delete(id);
        return ResponseEntity.noContent().build();
        
    }
}
