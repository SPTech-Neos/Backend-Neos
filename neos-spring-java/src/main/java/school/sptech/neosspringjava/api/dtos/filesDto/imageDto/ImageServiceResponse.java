package school.sptech.neosspringjava.api.dtos.filesDto.imageDto;

import school.sptech.neosspringjava.domain.model.Service;

public record ImageServiceResponse(Integer id, String name, String path, String fileExtension, Float fileSize,
        Integer serviceFk) {

}
