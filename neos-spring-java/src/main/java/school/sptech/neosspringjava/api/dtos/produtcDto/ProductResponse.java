package school.sptech.neosspringjava.api.dtos.produtcDto;

import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.ProductType;
import school.sptech.neosspringjava.domain.model.Status;

public record ProductResponse(Integer id,String name, String brand,String imgUrl,  ProductType type, Double value, Status status, Establishment establishment){}
