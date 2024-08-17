package school.sptech.neosspringjava.service.couponAvailableService;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.model.couponAvailable.CouponAvailable;
import school.sptech.neosspringjava.domain.repository.couponAvaliableRepository.*;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.*;
import school.sptech.neosspringjava.api.mappers.couponAvailableMapper.CouponAvailableMapper;


@Service
@RequiredArgsConstructor
public class CouponAvailableService {

    private final CouponAvailableMapper couponAvailableMapper;
    private final CouponAvaliableRepository  couponAvailableRepository;
    private final DiscountTypeRepository discountTypeRepository;
    private final CouponRepository couponRepository;
    private final EstablishmentRepository establishmentRepository;

    public List<CouponAvailableResponse> findAll() {
        return couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.findAll());
    }

    public CouponAvailableResponse findById(Integer id) {
        return couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.findById(id).get());
    }

    public CouponAvailableResponse save(CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = CouponAvailable.builder()
                .discount(couponAvailableRequest.discount())
                .discountType(discountTypeRepository.findById(couponAvailableRequest.fkDiscount()).get())
                .establishment(establishmentRepository.findById(couponAvailableRequest.fkEstablishment()).get())
                .coupon(couponRepository.findById(couponAvailableRequest.fkCoupon()).get())
                .build();
        return couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.save(couponAvailable));
    }

    public CouponAvailableResponse update(Integer id, CouponAvailableRequest couponAvailableRequest) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).get();
        couponAvailable.setDiscount(couponAvailableRequest.discount());
        couponAvailable.setDiscountType(discountTypeRepository.findById(couponAvailableRequest.fkDiscount()).get());
        couponAvailable.setEstablishment(establishmentRepository.findById(couponAvailableRequest.fkEstablishment()).get());
        couponAvailable.setCoupon(couponRepository.findById(couponAvailableRequest.fkCoupon()).get());
        return couponAvailableMapper.toCouponAvailableResponse(couponAvailableRepository.save(couponAvailable));
    }

    public void delete(Integer id) {
        CouponAvailable couponAvailable = couponAvailableRepository.findById(id).get();
        couponAvailableRepository.delete(couponAvailable);
    }

}
