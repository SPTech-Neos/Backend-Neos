package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeRequest;
import school.sptech.neosspringjava.api.dtos.productTypeDto.ProductTypeResponse;
import school.sptech.neosspringjava.domain.model.ProductType;

@Component
public class ProductTypeMapper {

    public ProductTypeResponse toResponse(ProductType productType) {
        return new ProductTypeResponse(productType.getId(), productType.getName(), productType.getSpecification());
    }

    public List<ProductTypeResponse> toResponseList(List<ProductType> productTypes) {
        return productTypes.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductType toProductType(ProductTypeRequest productTypeRequest) {
        return ProductType.builder().name(productTypeRequest.name()).specification(productTypeRequest.specification()).build();
    }

}
