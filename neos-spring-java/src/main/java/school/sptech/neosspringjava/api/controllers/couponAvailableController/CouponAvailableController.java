package school.sptech.neosspringjava.api.controllers.couponAvailableController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.mappers.couponAvailableMapper.CouponAvailableMapper;
import school.sptech.neosspringjava.domain.model.couponAvailable.CouponAvailable;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.couponAvaliableRepository.CouponAvaliableRepository;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.service.companyService.CouponService;
import school.sptech.neosspringjava.api.dtos.couponDto.*;;

@RestController
@RequestMapping("/couponAvailable")
@RequiredArgsConstructor
public class CouponAvailableController {

   private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponResponse> save(@Valid @RequestBody CouponRequest couponRequest) {
        return ResponseEntity.ok(couponService.save(couponRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(couponService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> update(@PathVariable Integer id, @Valid @RequestBody CouponRequest couponRequest) {
        return ResponseEntity.ok(couponService.update(id, couponRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        couponService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
    