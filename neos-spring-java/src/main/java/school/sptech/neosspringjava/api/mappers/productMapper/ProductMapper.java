package school.sptech.neosspringjava.api.mappers.productMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.domain.model.product.Product;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getBrand(), product.getFkProductType(), product.getFkEstablishment());
    }

    public List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder().name(productRequest.name()).brand(productRequest.brand()).fkProductType(productRequest.fkProductType()).fkEstablishment(productRequest.fkEstablishment()).build();
    }
}
