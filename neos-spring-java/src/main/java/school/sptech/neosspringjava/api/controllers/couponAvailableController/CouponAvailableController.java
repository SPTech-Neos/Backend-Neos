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
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableRequest;
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableResponse;
import school.sptech.neosspringjava.api.mappers.couponAvailableMapper.CouponAvailableMapper;
import school.sptech.neosspringjava.domain.model.couponAvailable.CouponAvailable;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.couponAvaliableRepository.CouponAvaliableRepository;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.service.coupon.CouponService;
import school.sptech.neosspringjava.service.couponAvailableService.CouponAvailableService;

@RestController
@RequestMapping("/couponAvailable")
@RequiredArgsConstructor
public class CouponAvailableController {

    private final CouponAvailableService couponAvailableService;

    @PostMapping
    public ResponseEntity<CouponAvailableResponse> save(
            @Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        return ResponseEntity.ok(couponAvailableService.save(couponAvailableRequest));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(couponAvailableService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> update(@PathVariable Integer id,
            @Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        return ResponseEntity.ok(couponAvailableService.update(id, couponAvailableRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        couponAvailableService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CouponAvailableResponse>> findAll() {
        return ResponseEntity.ok(couponAvailableService.findAll());
    }

}