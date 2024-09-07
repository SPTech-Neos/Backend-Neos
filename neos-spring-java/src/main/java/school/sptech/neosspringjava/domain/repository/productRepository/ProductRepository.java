package school.sptech.neosspringjava.domain.repository.productRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findAllByEstablishment(Establishment establishmentId);

    @Query("SELECT p FROM Product p WHERE p.establishment.id = :id")
    List<Product> findProductsByEstablishmentId(@Param("id") Integer id);

}
