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



    @Test
    void testUpdate() {
      

    }

}
