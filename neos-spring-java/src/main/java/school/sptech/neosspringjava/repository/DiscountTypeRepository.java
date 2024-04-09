package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.discountType.DiscountType;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Integer>{


}