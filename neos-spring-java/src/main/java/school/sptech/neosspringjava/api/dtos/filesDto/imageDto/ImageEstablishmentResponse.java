package school.sptech.neosspringjava.api.dtos.filesDto.imageDto;

import school.sptech.neosspringjava.domain.model.Establishment;

public record ImageEstablishmentResponse(Integer id, String name, String path, String fileExtension, Float fileSize, Integer establishmentFk) {

}
