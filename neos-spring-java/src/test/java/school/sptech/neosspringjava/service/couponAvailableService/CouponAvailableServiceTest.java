package school.sptech.neosspringjava.service.couponAvailableService;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.jsonwebtoken.lang.Assert;
import school.sptech.neosspringjava.domain.model.couponAvailable.CouponAvailable;
import school.sptech.neosspringjava.domain.repository.couponAvaliableRepository.CouponAvaliableRepository;
import school.sptech.neosspringjava.domain.repository.couponRepository.CouponRepository;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableRequest;
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableResponse;
import school.sptech.neosspringjava.api.mappers.couponAvailableMapper.CouponAvailableMapper;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CouponAvailableServiceTest {

    @Mock
    private CouponAvaliableRepository couponAvailableRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private DiscountTypeRepository discountTypeRepository;

    @Mock
    private EstablishmentRopository establishmentRopository;

    @Mock
    private CouponAvailableMapper couponAvailableMapper;

    @InjectMocks
    private CouponAvailableService couponAvailableService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<CouponAvailable> couponAvailables = Arrays.asList(
                new CouponAvailable(1, null, 10.0, null, null, null),
                new CouponAvailable(2, null, 20.0, null, null, null)
        );
        List<CouponAvailableResponse> couponAvailableResponses = Arrays.asList(
                new CouponAvailableResponse(1, null, 10.0, null, null, null),
                new CouponAvailableResponse(2, null, 20.0, null, null, null)
        );

        when(couponAvailableRepository.findAll()).thenReturn(couponAvailables);

        List<CouponAvailableResponse> result = couponAvailableService.findAll();

        assertEquals(couponAvailableResponses, result);
        verify(couponAvailableRepository).findAll();
    }

    @Test
    void testFindById() {
        CouponAvailable couponAvailable = new CouponAvailable(1, null, 10.0, null, null, null);
        CouponAvailableResponse couponAvailableResponse = new CouponAvailableResponse(1, null, 10.0, null, null, null);

        when(couponAvailableRepository.findById(1)).thenReturn(Optional.of(couponAvailable));

        CouponAvailableResponse result = couponAvailableService.findById(1);

        assertEquals(couponAvailableResponse, result);
        verify(couponAvailableRepository).findById(1);
    }

    @Test
    void testSave() {
        CouponAvailableRequest couponAvailableRequest = new CouponAvailableRequest(null, 10.0, 1, 1, 1);
        CouponAvailable couponAvailable = new CouponAvailable(1, null, 10.0, null, null, null);
        CouponAvailableResponse couponAvailableResponse = new CouponAvailableResponse(1, null, 10.0, null, null, null);

        when(discountTypeRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(establishmentRopository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(couponRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(couponAvailableRepository.save(any(CouponAvailable.class))).thenReturn(couponAvailable);

    }

    @Test
    void testUpdate() {
        CouponAvailableRequest couponAvailableRequest = new CouponAvailableRequest(null, 20.0, 1, 1, 1);
        CouponAvailable couponAvailable = new CouponAvailable(1, null, 10.0, null, null, null);
        CouponAvailable updatedCouponAvailable = new CouponAvailable(1, null, 20.0, null, null, null);
        CouponAvailableResponse couponAvailableResponse = new CouponAvailableResponse(1, null, 20.0, null, null, null);

        when(couponAvailableRepository.findById(1)).thenReturn(Optional.of(couponAvailable));
        when(discountTypeRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(establishmentRopository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(couponRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        when(couponAvailableRepository.save(any(CouponAvailable.class))).thenReturn(updatedCouponAvailable);


    }

    @Test
    void testDelete() {
        CouponAvailable couponAvailable = new CouponAvailable(1, null, 10.0, null, null, null);

        when(couponAvailableRepository.findById(1)).thenReturn(Optional.of(couponAvailable));

        couponAvailableService.delete(1);

        verify(couponAvailableRepository).findById(1);
        verify(couponAvailableRepository).delete(couponAvailable);
    }
}
