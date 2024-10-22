package school.sptech.neosspringjava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.ProductType;
import school.sptech.neosspringjava.domain.model.Status;
import school.sptech.neosspringjava.domain.repository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.ProductTypeRepository;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductRequest;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.ProductMapper;



@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper ProductMapper;
    private final EstablishmentService establishmentService;
    private final ProductTypeService productTypeService;
    private final StatusService statusService;
    private final ProductTypeRepository productTypeRepository;

    public ProductResponse save(ProductRequest productRequest) {

        ProductType productType = productTypeRepository.findById(productRequest.type()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Produto não encontrado"));

        Establishment establishment = establishmentService.findById(productRequest.establishment());
        if (establishment.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
        Status status = statusService.findById(productRequest.status());
        if (status.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
        }
        Product product = ProductMapper.toProduct(productRequest, productType, establishment, status);
        productRepository.save(product);
        return ProductMapper.toProductResponse(product);
    }

    public ProductResponse update(Integer id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    
        ProductType productType = productTypeRepository.findById(productRequest.type())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de produto não encontrado"));
        
        Establishment establishment = establishmentService.findById(productRequest.establishment());
        if (establishment.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
        Status status = statusService.findById(productRequest.status());
        if (status.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
        }
    
        product.setName(productRequest.name());
        product.setBrand(productRequest.brand());
        product.setType(productType);
        product.setImgUrl(productRequest.imgUrl());
        product.setEstablishment(establishment);
        product.setPrice(productRequest.value());
        product.setStatus(status);

        productRepository.save(product);    
        
        return ProductMapper.toProductResponse(product);
    }

    public ProductResponse partialUpdate(Integer id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    
        if (productRequest.type() != null) {
            ProductType productType = productTypeRepository.findById(productRequest.type())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de produto não encontrado"));
            product.setType(productType);
        }
    
        if (productRequest.establishment() != null) {
            Establishment establishment = establishmentService.findById(productRequest.establishment());
        if (establishment.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
            product.setEstablishment(establishment);
        }
        if (productRequest.status() != null) {
            Status status = statusService.findById(productRequest.status());
            if (status.equals(null)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
            }
            product.setStatus(status);
        }
        
    
        if (productRequest.brand() != null && !productRequest.brand().isEmpty()) {
            product.setBrand(productRequest.brand());
        }
    
        if (productRequest.imgUrl() != null && !productRequest.imgUrl().isEmpty()) {
            product.setImgUrl(productRequest.imgUrl());
        }
    
        if (productRequest.value() != null) {
            product.setPrice(productRequest.value());
        }
    
        if (productRequest.name() != null && !productRequest.name().isEmpty()) {
            product.setName(productRequest.name());
        }
    
        productRepository.save(product);
    
        return ProductMapper.toProductResponse(product);
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

    public List<ProductResponse> findProductsByEstablishmentId(Integer id) {
        List<Product> products = productRepository.findProductsByEstablishmentId(id);
        return ProductMapper.toProductResponse(products);
    }

    public ProductResponse updateProductStatus(Integer id, Integer statusId) {
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        if (statusId != null) {
            Status status = statusService.findById(statusId);
            if (status.equals(null)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
            }
            product.setStatus(status);;
        }
        productRepository.save(product);
    
        return ProductMapper.toProductResponse(product);
    }

    public List<ProductResponse> findProductsByEstablishmentIdAndStatus(Integer id, String status) {
        List<Product> products = productRepository.findProductsByEstablishmentIdAndStatus(id, status);
        return ProductMapper.toProductResponse(products);
    }

    public List<ProductResponse> findProductsByEstablishmentIdAndStatus(Integer id, Integer status) {
        List<Product> products = productRepository.findByEstablishmentIdAndStatusId(id, status);
        return ProductMapper.toProductResponse(products);
    }

}