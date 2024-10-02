package school.sptech.neosspringjava.api.dtos.produtcDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.ProductType;

@Builder
public record ProductRequest(String name,String brand,String imgUrl,  Integer type,Double value, Integer establishment ) {

}
