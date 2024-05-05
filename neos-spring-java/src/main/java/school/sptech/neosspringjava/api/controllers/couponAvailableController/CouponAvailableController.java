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

@RestController
@RequestMapping("/couponAvailable")
@RequiredArgsConstructor
public class CouponAvailableController {

    private final CouponAvailableMapper couponAvailableMapper;
    private final CouponAvaliableRepository  couponAvailableRepository;
    private final DiscountTypeRepository discountTypeRepository;
    private final CouponRepository couponRepository;
    private final EstablishmentRopository establishmentRopository;

    @GetMapping
    public ResponseEntity<List<CouponAvailableResponse>> findAll() {
        return ResponseEntity.ok(couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<CouponAvailableResponse> save(@Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = CouponAvailable.builder()
                .discount(couponAvailableRequest.discount())
                .discountType(discountTypeRepository.findById(couponAvailableRequest.fkDiscount()).get())
                .establishment(establishmentRopository.findById(couponAvailableRequest.fkEstablishment()).get())
                .coupon(couponRepository.findById(couponAvailableRequest.fkCoupon()).get())
                .build();
        return ResponseEntity.ok(couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.save(couponAvailable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponAvailableResponse> update(@PathVariable Integer id, @Valid @RequestBody CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).get();
        couponAvailable.setDiscount(couponAvailableRequest.discount());
        couponAvailable.setDiscountType(discountTypeRepository.findById(couponAvailableRequest.fkDiscount()).get());
        couponAvailable.setEstablishment(establishmentRopository.findById(couponAvailableRequest.fkEstablishment()).get());
        couponAvailable.setCoupon(couponRepository.findById(couponAvailableRequest.fkCoupon()).get());
        return ResponseEntity.ok(couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.save(couponAvailable)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        couponAvailableRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    
}
    