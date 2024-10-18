package school.sptech.neosspringjava.api.dtos.filesDto.imageDto;

import school.sptech.neosspringjava.domain.model.Client;

public record ImageClientResponse(Integer id, String name, String path, String fileExtension, Float fileSize, Integer clientFk) {

}
