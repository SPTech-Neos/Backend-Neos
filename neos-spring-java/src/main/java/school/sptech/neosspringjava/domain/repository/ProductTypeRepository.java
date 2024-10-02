package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>  {

}
