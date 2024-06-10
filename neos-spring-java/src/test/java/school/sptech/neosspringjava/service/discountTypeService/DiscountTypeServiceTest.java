package school.sptech.neosspringjava.service.discountTypeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import school.sptech.neosspringjava.domain.model.discountType.DiscountType;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeRequest;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeResponse;
import school.sptech.neosspringjava.api.mappers.discountTypeMapper.DiscountTypeMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountTypeServiceTest {

    @Mock
    private DiscountTypeRepository discountTypeRepository;

    @Mock
    private DiscountTypeMapper discountTypeMapper;

    @InjectMocks
    private DiscountTypeService discountTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        DiscountTypeRequest discountTypeRequest = new DiscountTypeRequest("DiscountType1");
        DiscountTypeResponse discountTypeResponse = new DiscountTypeResponse(1, "DiscountType1");

        when(discountTypeMapper.toDiscountType(discountTypeRequest)).thenReturn(new DiscountType(1, "DiscountType1"));
        when(discountTypeRepository.save(any(DiscountType.class))).thenReturn(new DiscountType(1, "DiscountType1"));
        when(discountTypeMapper.toDiscountTypeResponse(any(DiscountType.class))).thenReturn(discountTypeResponse);

        DiscountTypeResponse result = discountTypeService.save(discountTypeRequest);

        assertEquals(discountTypeResponse, result);
        verify(discountTypeRepository).save(any(DiscountType.class));
    }

    @Test
    void testUpdate() {
        DiscountTypeRequest discountTypeRequest = new DiscountTypeRequest("UpdatedDiscountType");
        DiscountTypeResponse discountTypeResponse = new DiscountTypeResponse(1, "UpdatedDiscountType");

        when(discountTypeMapper.toDiscountType(discountTypeRequest, 1)).thenReturn(new DiscountType(1, "UpdatedDiscountType"));
        when(discountTypeRepository.save(any(DiscountType.class))).thenReturn(new DiscountType(1, "UpdatedDiscountType"));
        when(discountTypeMapper.toDiscountTypeResponse(any(DiscountType.class))).thenReturn(discountTypeResponse);

        DiscountTypeResponse result = discountTypeService.update(discountTypeRequest, 1);

        assertEquals(discountTypeResponse, result);
        verify(discountTypeRepository).save(any(DiscountType.class));
    }

    @Test
    void testDelete() {
        discountTypeService.delete(1);

        verify(discountTypeRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        DiscountType discountType = new DiscountType(1, "DiscountType1");
        DiscountTypeResponse discountTypeResponse = new DiscountTypeResponse(1, "DiscountType1");

        when(discountTypeRepository.findById(1)).thenReturn(Optional.of(discountType));
        when(discountTypeMapper.toDiscountTypeResponse(discountType)).thenReturn(discountTypeResponse);

        DiscountTypeResponse result = discountTypeService.findById(1);

        assertEquals(discountTypeResponse, result);
        verify(discountTypeRepository).findById(1);
    }

  }