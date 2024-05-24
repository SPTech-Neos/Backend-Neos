package school.sptech.neosspringjava.api.dtos.produtcDto;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.productType.ProductType;

public record ProductResponse(Integer id, String name,String brand, ProductType productType, Establishment establishment, Double price) {

}
