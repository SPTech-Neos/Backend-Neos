package school.sptech.neosspringjava.api.dtos.couponAvailableDto;

import java.util.Date;

import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.model.discountType.DiscountType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public record CouponAvailableResponse(Integer id, Date expirationDate, Double discount, DiscountType discountType, Establishment etablishment, Coupon coupon) {

}
