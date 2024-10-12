package school.sptech.neosspringjava.api.dtos.produtcDto;

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.productType.ProductType;
import school.sptech.neosspringjava.domain.model.status.Status;

public record ProductResponse(Integer id,String name, String brand,String imgUrl,  ProductType type, Double value, Status status){}
