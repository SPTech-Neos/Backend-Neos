package school.sptech.neosspringjava.service.discountTypeService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.repository.discountTypeRepository.DiscountTypeRepository;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeRequest;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.DiscountTypeResponse;
import school.sptech.neosspringjava.api.mappers.discountTypeMapper.*;

@Service
@RequiredArgsConstructor
public class DiscountTypeService {

    private final DiscountTypeRepository discountTypeRepository;
    private final DiscountTypeMapper discountTypeMapper;

    public DiscountTypeResponse save(DiscountTypeRequest discountTypeRequest) {
        return discountTypeMapper.toDiscountTypeResponse(discountTypeRepository.save(discountTypeMapper.toDiscountType(discountTypeRequest)));
    }

    public DiscountTypeResponse update(DiscountTypeRequest discountTypeRequest, Integer id) {
        return discountTypeMapper.toDiscountTypeResponse(discountTypeRepository.save(discountTypeMapper.toDiscountType(discountTypeRequest, id)));
    }

    public void delete(Integer id) {
        discountTypeRepository.deleteById(id);
    }

    public DiscountTypeResponse findById(Integer id) {
        return discountTypeMapper.toDiscountTypeResponse(discountTypeRepository.findById(id).orElseThrow());
    }

    public List<DiscountTypeResponse> findAll() {
        return discountTypeRepository.findAll().stream().map(discountTypeMapper::toDiscountTypeResponse).collect(Collectors.toList());
    }
    
}
