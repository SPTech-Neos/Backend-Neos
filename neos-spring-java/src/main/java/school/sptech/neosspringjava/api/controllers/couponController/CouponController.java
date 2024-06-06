package school.sptech.neosspringjava.api.controllers.couponController;

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
import school.sptech.neosspringjava.api.dtos.couponDto.CouponRequest;
import school.sptech.neosspringjava.api.dtos.couponDto.CouponResponse;
import school.sptech.neosspringjava.api.mappers.couponMapper.CuponMapper;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.service.companyService.*;
import school.sptech.neosspringjava.service.coupon.CouponService;

@RestController
@RequestMapping("/cupon")
@RequiredArgsConstructor
public class CouponController {

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
