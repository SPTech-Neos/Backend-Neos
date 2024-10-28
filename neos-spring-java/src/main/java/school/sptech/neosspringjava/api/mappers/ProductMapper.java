package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.ProductType;
import school.sptech.neosspringjava.domain.model.Status;


@Component
public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getBrand(), product.getImgUrl(), product.getType(), product.getPrice(), product.getStatus());
    }

    public static List<ProductResponse> toProductResponse(List<Product> product) {
        return product.stream().map(ProductMapper::toProductResponse).collect(Collectors.toList());
    }

    public static Product toProduct(ProductRequest productRequest, ProductType productType, Establishment establishment, Status status){
        Product product = Product.builder()
                .name(productRequest.name())
                .brand(productRequest.brand())
                .type(productType)
                .imgUrl(productRequest.imgUrl())
                .establishment(establishment)
                .price(productRequest.value())
                .status(status)
                .build();
        return product;
    }

    public static List<Product> toProduct(List<ProductRequest> productRequests, List<ProductType> productTypes, List<Establishment> establishments, List<Status>status) {
        if (productRequests.size() != productTypes.size() || productRequests.size() != establishments.size() || productRequests.size() != status.size()) {
            throw new IllegalArgumentException("As listas de ProductRequest, ProductType, Establishment e status devem ter o mesmo tamanho.");
        }
        return productRequests.stream().map(productRequest -> {
            int index = productRequests.indexOf(productRequest);
            return ProductMapper.toProduct(productRequest, productTypes.get(index), establishments.get(index), status.get(index));
        }).collect(Collectors.toList());
    }

   
}