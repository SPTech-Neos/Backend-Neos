package school.sptech.neosspringjava.api.mappers.couponMapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import school.sptech.neosspringjava.api.dtos.couponDto.*;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;



@Component
public class CuponMapper {

    public static CouponResponse toCouponResponse(Coupon coupon) {
        return new CouponResponse(coupon.getId(), coupon.getName());
    }

    public static List<CouponResponse> toCouponResponse(List<Coupon> coupons) {
        return coupons.stream().map(CuponMapper::toCouponResponse).collect(Collectors.toList());
    }

    public static Coupon toCoupon(CouponRequest couponRequest) {

        return Coupon.builder()
                .name(couponRequest.name())
                .build();
    }
}
