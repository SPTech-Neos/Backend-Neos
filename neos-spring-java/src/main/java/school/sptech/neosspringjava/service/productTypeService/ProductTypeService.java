package school.sptech.neosspringjava.service.productTypeService;

import java.util.List;

import org.hibernate.jdbc.Expectations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeRequest;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeResponse;
import school.sptech.neosspringjava.api.mappers.productTypeMapper.ProductTypeMapper;
import school.sptech.neosspringjava.domain.model.productType.ProductType;
import school.sptech.neosspringjava.domain.repository.productTypeRepository.ProductTypeRepository;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public void delete(Integer id) {
        productTypeRepository.deleteById(id);
    }

    public void update(ProductTypeRequest productTypeRequest, Integer id) {
        ProductType productType = productTypeMapper.toProductType(productTypeRequest);
        productType.setId(id);
        productTypeRepository.save(productType);
    }

    public ProductTypeResponse save(ProductTypeRequest productTypeRequest) {
        ProductType productType = productTypeMapper.toProductType(productTypeRequest);
        return productTypeMapper.toResponse(productTypeRepository.save(productType));
    }

    public ProductTypeResponse findById(Integer id) {
        return productTypeRepository.findById(id).map(productTypeMapper::toResponse).orElseThrow();
    }

    public List<ProductTypeResponse> findAll() {
       try{
        return productTypeMapper.toResponseList(productTypeRepository.findAll());
       }catch(Exception e){
              return null;
       }
    }

    
}
