package school.sptech.neosspringjava.service.coupon;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.api.dtos.couponDto.CouponRequest;
import school.sptech.neosspringjava.api.dtos.couponDto.CouponResponse;
import school.sptech.neosspringjava.api.mappers.couponMapper.CuponMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CuponMapper couponMapper;

    @InjectMocks
    private CouponService couponService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CouponRequest couponRequest = new CouponRequest("Discount");
        Coupon coupon = new Coupon(1, "Discount");
        CouponResponse couponResponse = new CouponResponse(null, "Discount");

        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponResponse result = couponService.save(couponRequest);

        assertEquals(couponResponse, result);
    }

    @Test
    void testFindById() {
        Coupon coupon = new Coupon(1, "Discount");
        CouponResponse couponResponse = new CouponResponse(1, "Discount");

        when(couponRepository.findById(1)).thenReturn(Optional.of(coupon));

        CouponResponse result = couponService.findById(1);

        assertEquals(couponResponse, result);
        verify(couponRepository).findById(1);
    }

    @Test
    void testUpdate() {
        CouponRequest couponRequest = new CouponRequest("Discount");
        Coupon coupon = new Coupon(1, "Discount");
        CouponResponse couponResponse = new CouponResponse(1, "Discount");

        Coupon updatedCoupon = new Coupon(1, "Super Discount");
       

        when(couponRepository.findById(1)).thenReturn(Optional.of(coupon));
        when(couponRepository.save(any(Coupon.class))).thenReturn(updatedCoupon);

        CouponResponse result = couponService.update(1, couponRequest);

        assertEquals(couponResponse, result);
        verify(couponRepository).findById(1);
        verify(couponRepository).save(any(Coupon.class));
    }

    @Test
    void testDelete() {
        Coupon coupon = new Coupon(1, "Discount");

        when(couponRepository.findById(1)).thenReturn(Optional.of(coupon));

        couponService.delete(1);

        verify(couponRepository).findById(1);
        verify(couponRepository).delete(coupon);
    }
}
