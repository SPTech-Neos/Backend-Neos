package school.sptech.neosspringjava.api.dtos.produtcDto;

import lombok.Builder;

@Builder
public record ProductRequest( String name,String brand, Integer fkProductType, Integer fkEstablishment) {

}
