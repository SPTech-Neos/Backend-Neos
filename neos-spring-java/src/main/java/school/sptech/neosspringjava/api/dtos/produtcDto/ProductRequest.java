package school.sptech.neosspringjava.api.dtos.produtcDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.productType.ProductType;

@Builder
public record ProductRequest(Integer id, String name,String brand,  ProductType productType, Establishment establishment, Double price) {

}
