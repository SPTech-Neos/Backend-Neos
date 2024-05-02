package school.sptech.neosspringjava.domain.repository.productRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.neosspringjava.domain.model.product.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer>{
}
