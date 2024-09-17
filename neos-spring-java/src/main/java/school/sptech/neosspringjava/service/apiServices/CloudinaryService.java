package school.sptech.neosspringjava.service.apiServices;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import school.sptech.neosspringjava.api.dtos.filesDto.FileResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.ImageResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.MesageResponse;
import school.sptech.neosspringjava.config.CloudinaryConfig;
import school.sptech.neosspringjava.domain.model.image.Image;

@Service
public class CloudinaryService {
    private final Path fileStorageLocation;
    private static Cloudinary cloudinary;

    private static final List<String> extencoesLiberadas = List.of("jpg", "png", "gif", "jpeg", "webp");	

    public CloudinaryService(CloudinaryConfig fileStorageApiProperties) {
        this.fileStorageLocation = Paths.get(fileStorageApiProperties.getUploadDir())
                .toAbsolutePath().normalize();

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzutcf8qe",
                "api_key", "167129722689288",
                "api_secret", "OeT7FMp5afVS4DY5VGvr_Z9zBAk",
                "secure", true));
    }

    public static Image  uploadImg(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Double tamanho = file.getSize() / 1024.0;
        tamanho = Math.round(tamanho * 100.0) / 100.0;
        String extencao = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (!extencoesLiberadas.contains(extencao)) {
            return null;
        }

        try {

            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName));

            String fileDownloadUri = (String) uploadResult.get("url");

            return Image.builder()
                    .name(fileName)
                    .path(fileDownloadUri)
                    .fileExtension(extencao)
                    .fileSize(tamanho.floatValue())
                    .build();

        } catch (Exception e) {

         return null;

        }

    }

    
}
