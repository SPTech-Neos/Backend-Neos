package school.sptech.neosspringjava.domain.repository.productTypeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.productType.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>  {

}
