package school.sptech.neosspringjava.api.dtos.filesDto.imageDto;

import school.sptech.neosspringjava.domain.model.Product;

public record ImageProductResponse(Integer id, String name, String path, String fileExtension, Float fileSize, Integer productFk) {

}
