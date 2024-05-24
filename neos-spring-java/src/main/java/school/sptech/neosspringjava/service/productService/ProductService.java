package school.sptech.neosspringjava.service.productService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.productMapper.ProductMapper;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse save(ProductRequest productRequest) {
        Product product = ProductMapper.toProduct(productRequest);
        productRepository.save(product);
        return ProductMapper.toProductResponse(product);
    }

    public ProductResponse update(Integer id, ProductRequest productRequest) {
        Optional<Product> productOp = productRepository.findById(id);
        if (productOp.isPresent()) {
            Product product = productOp.get();
            product.setName(productRequest.name());
            product.setBrand(productRequest.brand());
            product.setProductType(productRequest.productType());
            product.setEstablishment(productRequest.establishment());
            product.setPrice(productRequest.price());
            productRepository.save(product);
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
        Optional<Product> productOp = productRepository.findById(id);
        return productOp.map(ProductMapper::toProductResponse).orElse(null);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
