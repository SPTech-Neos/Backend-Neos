package school.sptech.neosspringjava.domain.repository.productRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.neosspringjava.domain.model.product.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer>{
//@Query("SELECT p FROM Product p WHERE p.id = :productId AND p.data BETWEEN :startDate AND :endDate")
List<Product> findByIdAndDataBetween(Long productId, LocalDate startDate, LocalDate endDate);
}
