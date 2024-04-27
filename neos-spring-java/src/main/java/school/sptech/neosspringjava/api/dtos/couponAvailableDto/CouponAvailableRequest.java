package school.sptech.neosspringjava.api.dtos.couponAvailableDto;


import java.util.Date;

import lombok.Builder;

@Builder
public record CouponAvailableRequest( Date expirationDate, Double discount, Integer fkDiscount, Integer fkEstablishment, Integer fkCoupon) {

}
