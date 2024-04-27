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

@RestController
@RequestMapping("/cupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponRepository cuponRepository;
    private final CuponMapper cuponMapper;

    @GetMapping
    public ResponseEntity<List<CouponResponse>> getAllCupon() {
       
        List<Coupon> cupon = cuponRepository.findAll();

        return ResponseEntity.ok().body(cuponMapper.toCouponResponse(cupon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getCuponById(@PathVariable int id) {
        Coupon cupon = cuponRepository.findById(id).orElseThrow();
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cuponMapper.toCouponResponse(cupon));
    }

    @PostMapping
    public ResponseEntity<CouponResponse> createCupon(@Valid @RequestBody CouponRequest cuponRequest) {
        Coupon cupon = cuponMapper.toCoupon(cuponRequest);
        cuponRepository.save(cupon);
        return ResponseEntity.ok().body(cuponMapper.toCouponResponse(cupon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> updateCupon(@PathVariable int id, @Valid @RequestBody CouponRequest cuponRequest) {
        Coupon cupon = cuponRepository.findById(id).orElseThrow();
        cupon.setName(cuponRequest.name());
        cuponRepository.save(cupon);
        return ResponseEntity.ok().body(cuponMapper.toCouponResponse(cupon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCupon(@PathVariable int id) {
        Coupon cupon = cuponRepository.findById(id).orElseThrow();
        cuponRepository.delete(cupon);
        return ResponseEntity.noContent().build();
    }
    
}
