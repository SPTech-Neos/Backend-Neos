package school.sptech.neosspringjava.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findAllByEstablishment(Establishment establishmentId);

    @Query("SELECT p FROM Product p WHERE p.establishment.id = :id")
    List<Product> findProductsByEstablishmentId(@Param("id") Integer id);

    @Query("SELECT p FROM Product p WHERE p.establishment.id = :id AND p.status.name = :status")
    List<Product> findProductsByEstablishmentIdAndStatus(@Param("id") Integer id, @Param("status") String status);

    @Query("SELECT p FROM Product p WHERE p.establishment.id = :establishmentId AND p.status.id = :statusId")
    List<Product> findByEstablishmentIdAndStatusId(@Param("establishmentId") Integer establishmentId, @Param("statusId") Integer statusId);
    

}