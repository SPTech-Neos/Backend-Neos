package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.coupon.Coupon;

public interface CouponRepository extends JpaRepository <Coupon , Integer>{

}
