package school.sptech.neosspringjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.productType.ProductType;

public interface ProductTypeRopository extends JpaRepository <ProductType, Integer>{

}
