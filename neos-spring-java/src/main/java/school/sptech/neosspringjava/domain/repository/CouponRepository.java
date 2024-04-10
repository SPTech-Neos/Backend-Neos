package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.couponAvailable.coupon.Coupon;

public interface CouponRepository extends JpaRepository <Coupon , Integer>{

}
