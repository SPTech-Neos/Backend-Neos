package school.sptech.neosspringjava.service.productService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.productMapper.ProductMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.productType.ProductType;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.productTypeRepository.ProductTypeRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper ProductMapper;
    private final EstablishmentRopository establishmentRopository;
    private final ProductTypeRepository productTypeRepository;

    public ProductResponse save(ProductRequest productRequest) {

        ProductType productType = productTypeRepository.findById(productRequest.type()).orElseThrow();
        Establishment establishment = establishmentRopository.findById(productRequest.establishment()).orElseThrow();
        Product product = Product.builder()
                .name(productRequest.name())
                .brand(productRequest.brand())
                .type(productType)
                .imgUrl(productRequest.imgUrl())
                .establishment(establishment)
                .value(productRequest.value())
                .build();
        productRepository.save(product);
        return ProductMapper.toProductResponse(product);
    }

    public ProductResponse update(Integer id, ProductRequest productRequest) {
        Optional<Product> productOp = productRepository.findById(id);
        if (productOp.isPresent()) {
            ProductType productType = productTypeRepository.findById(productRequest.type()).orElseThrow();
        Establishment establishment = establishmentRopository.findById(productRequest.establishment()).orElseThrow();
        Product product = Product.builder()
                .name(productRequest.name())
                .brand(productRequest.brand())
                .type(productType)
                .imgUrl(null)
                .establishment(establishment)
                .value(productRequest.value())
                .build();
            return ProductMapper.toProductResponse(product);
        } else {
            // Handle the case where the product is not found, e.g., throw an exception or return a default response
            return null;
        }
    }

    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toProductResponse(products);
    }

    public ProductResponse findById(Integer id) {
        try {
            Product product = productRepository.findById(id).orElseThrow();
            return ProductMapper.toProductResponse(product);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductResponse> findAllByEstablishment(Establishment establishment) {
    
        return ProductMapper.toProductResponse(productRepository.findAllByEstablishment(establishment));
    }

    public List<ProductResponse> findAllByEstablishments(List<Establishment> establishments) {
        List<ProductResponse> allProducts = new ArrayList<>();

        for (Establishment establishment : establishments) {
            List<ProductResponse> productsForEstablishment = findAllByEstablishment(establishment);
            allProducts.addAll(productsForEstablishment);
        }

        return allProducts;
    }

}
