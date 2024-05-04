package school.sptech.neosspringjava.domain.repository.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.product.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer>{

}
