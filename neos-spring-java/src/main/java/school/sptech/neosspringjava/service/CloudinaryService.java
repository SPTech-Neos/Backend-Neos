package school.sptech.neosspringjava.service;

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
import school.sptech.neosspringjava.api.dtos.filesDto.MesageResponse;
import school.sptech.neosspringjava.config.CloudinaryConfig;

@Service
public class CloudinaryService {
    private final Path fileStorageLocation;
    private final Cloudinary cloudinary;

    private final List<String> extencoesLiberadas = new ArrayList<>();

    public CloudinaryService(CloudinaryConfig fileStorageApiProperties) {
        this.fileStorageLocation = Paths.get(fileStorageApiProperties.getUploadDir())
                .toAbsolutePath().normalize();

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzutcf8qe",
                "api_key", "167129722689288",
                "api_secret", "OeT7FMp5afVS4DY5VGvr_Z9zBAk",
                "secure", true));
    }

    public FileResponse uploadImg(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // tentei de maneiras diferentes, mas não consegui só dessa forma feia
        // extencoesLiberadas.add("jpg", "jpeg", "png"); não funcionou :(
        extencoesLiberadas.add("jpg");
        extencoesLiberadas.add("jpeg");
        extencoesLiberadas.add("png");

        Double tamanho = file.getSize() / 1024.0 ;
        // 2 casas decimais 1.80078125 == 1.80
        tamanho = Math.round(tamanho * 100.0) / 100.0;
        // Double tamanho = file.getSize()<=0 : 0.00 ?file.getSize() / 1024.0;  // tamanho em KB
        String extencao = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (!extencoesLiberadas.contains(extencao)) {
            return montarFileResponse(
                    fileName,
                    extencao,
                    tamanho,
                    null,
                    new MesageResponse("bad request",
                            400,
                            "Extensão de arquivo não permitida!",
                            null,
                            "uploadImg",
                            "/api/files/img-upload"));
        }

        FileResponse fileResponse;

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName));

            String fileDownloadUri = (String) uploadResult.get("url");

            fileResponse = montarFileResponse(
                    fileName,
                    extencao,
                    tamanho,
                    fileDownloadUri,
                    new MesageResponse("success",
                            201,
                            "Arquivo enviado com sucesso!",
                            null,
                            "uploadImg",
                            "/api/files/img-upload"));
            return fileResponse;
        } catch (Exception e) {

            fileResponse = montarFileResponse(
                    fileName,
                    extencao,
                    tamanho,
                    null,
                    new MesageResponse(e.getClass().getSimpleName(),
                            e.hashCode(),
                            "Falha ao enviar arquivo!",
                            e.getMessage(),
                            "uploadImg",
                            "/api/files/img-upload"));

            // verificar com o grupo se evios de requisições com erro devem ser logados no
            // banco de dados

            return fileResponse;

        }

    }



    private FileResponse montarFileResponse(String fileName, String fileType, Double size, String url, MesageResponse message) {
        return new FileResponse(fileName, fileType, size, url, message);
    }
}
