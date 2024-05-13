package school.sptech.neosspringjava.service.companyService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.api.dtos.couponDto.*;
import school.sptech.neosspringjava.api.mappers.couponMapper.CuponMapper;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CuponMapper couponMapper;

    public CouponResponse save(CouponRequest couponRequest) {
        Coupon coupon = CuponMapper.toCoupon(couponRequest);
        couponRepository.save(coupon);
        return couponMapper.toCouponResponse(coupon);
    }

    public CouponResponse findById(Integer id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        return couponMapper.toCouponResponse(coupon);
    }

    public CouponResponse update(Integer id, CouponRequest couponRequest) {
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        coupon.setName(couponRequest.name());
        couponRepository.save(coupon);
        return couponMapper.toCouponResponse(coupon);
    }

    public void delete(Integer id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        couponRepository.delete(coupon);
    }
    

  }