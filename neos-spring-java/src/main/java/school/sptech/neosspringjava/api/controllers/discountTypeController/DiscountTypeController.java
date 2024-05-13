package school.sptech.neosspringjava.api.controllers.discountTypeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeRequest;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeResponse;
import school.sptech.neosspringjava.service.discountTypeService.DiscountTypeService;

@RestController
@RequestMapping("/discountType")
@RequiredArgsConstructor
public class DiscountTypeController {

    private final DiscountTypeService discountTypeService;

    @PostMapping
    public ResponseEntity<DiscountTypeResponse> save(@RequestBody DiscountTypeRequest discountTypeRequest) {
        return ResponseEntity.ok(discountTypeService.save(discountTypeRequest));
    }

    @PutMapping("/{id}")

    public ResponseEntity<DiscountTypeResponse> update(@RequestBody DiscountTypeRequest discountTypeRequest,
            @PathVariable Integer id) {
        return ResponseEntity.ok(discountTypeService.update(discountTypeRequest, id));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        discountTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")

    public ResponseEntity<DiscountTypeResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(discountTypeService.findById(id));
    }

    @GetMapping

    public ResponseEntity<List<DiscountTypeResponse>> findAll() {
        return ResponseEntity.ok(discountTypeService.findAll());
    }

}
