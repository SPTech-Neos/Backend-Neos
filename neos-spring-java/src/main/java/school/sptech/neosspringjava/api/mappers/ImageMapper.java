package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.ImageResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageClientResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageEmployeeResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageEstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageProductResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageServiceResponse;
import school.sptech.neosspringjava.domain.model.Image;
import school.sptech.neosspringjava.domain.model.Local;

@Component
public class ImageMapper {

    public static Object toImageResponse(Image img, String entity) {

        switch (entity) {
            case "client":
                return new ImageClientResponse(img.getId(), img.getName(), img.getPath(), img.getFileExtension(),
                        img.getFileSize(), img.getClient().getId());
            case "product":
                return new ImageProductResponse(img.getId(), img.getName(), img.getPath(), img.getFileExtension(),
                        img.getFileSize(), img.getProduct().getId());
            case "service":
                return new ImageServiceResponse(img.getId(), img.getName(), img.getPath(), img.getFileExtension(),
                        img.getFileSize(), img.getService().getId());
            case "establishment":
                return new ImageEstablishmentResponse(img.getId(), img.getName(), img.getPath(), img.getFileExtension(),
                        img.getFileSize(), img.getEstablishment().getId());
            case "employee":
                return new ImageEmployeeResponse(img.getId(), img.getName(), img.getPath(), img.getFileExtension(),
                        img.getFileSize(), img.getEmployee().getId());
            default:
            return null;

        }

    }



    public static List<Object> toImageResponse(List<Image> img, String entity) {
        return img.stream().map(i -> (ImageResponse) toImageResponse(i, entity)).collect(Collectors.toList());
    }

}

