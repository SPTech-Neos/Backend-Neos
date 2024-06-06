package school.sptech.neosspringjava.api.dtos.produtcDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.productType.ProductType;

@Builder
public record ProductRequest(String name,String brand,String imgUrl,  Integer type,Double value, Integer establishment ) {

}
