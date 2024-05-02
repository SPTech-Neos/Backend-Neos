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
import school.sptech.neosspringjava.domain.repository.couponAvaliableRepository.CouponAvaliableRepository;

@RestController
@RequestMapping("/couponAvailable")
@RequiredArgsConstructor
public class CouponAvailableController {

    private final CouponAvailableMapper couponAvailableMapper;
    private final CouponAvaliableRepository  couponAvailableRepository;

    @GetMapping
    public ResponseEntity<List<CouponAvailableResponse>> getAllCouponAvailable() {
       
        List<CouponAvailable> couponAvailable = couponAvailableRepository.findAll();

        return ResponseEntity.ok().body(couponAvailableMapper.toCouponAvailableResponse(couponAvailable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> getCouponAvailableById(@PathVariable int id) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).orElseThrow();
        if (couponAvailable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(couponAvailableMapper.toCouponAvailableResponse(couponAvailable));
    }

    @PostMapping
    public ResponseEntity<CouponAvailableResponse> createCouponAvailable(@Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = couponAvailableMapper.toCouponAvailable(couponAvailableRequest);
        couponAvailableRepository.save(couponAvailable);
        return ResponseEntity.ok().body(couponAvailableMapper.toCouponAvailableResponse(couponAvailable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> updateCouponAvailable(@PathVariable int id, @Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).orElseThrow();
        couponAvailable.setExpirationDate(couponAvailableRequest.expirationDate());
        couponAvailable.setDiscount(couponAvailableRequest.discount());
        couponAvailable.setFkDiscount(couponAvailableRequest.fkDiscount());
        couponAvailable.setFkEstablishment(couponAvailableRequest.fkEstablishment());
        couponAvailable.setFkCoupon(couponAvailableRequest.fkCoupon());
        couponAvailableRepository.save(couponAvailable);
        return ResponseEntity.ok().body(couponAvailableMapper.toCouponAvailableResponse(couponAvailable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> deleteCouponAvailable(@PathVariable int id) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).orElseThrow();
        couponAvailableRepository.delete(couponAvailable);
        return ResponseEntity.ok().body(couponAvailableMapper.toCouponAvailableResponse(couponAvailable));
    }

    
}
    