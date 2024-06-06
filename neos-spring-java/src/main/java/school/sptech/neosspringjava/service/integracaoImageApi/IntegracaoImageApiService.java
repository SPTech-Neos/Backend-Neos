package school.sptech.neosspringjava.service.integracaoImageApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;
import school.sptech.neosspringjava.config.IntegracaoimageApiConfig;

@Service
public class IntegracaoImageApiService {
    private final Path fileStorageLocation;
    private final Cloudinary cloudinary;

    public IntegracaoImageApiService(IntegracaoimageApiConfig fileStorageApiProperties) {
        this.fileStorageLocation = Paths.get(fileStorageApiProperties.getUploadDir())
                .toAbsolutePath().normalize();

                this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dzutcf8qe",
            "api_key", "167129722689288",
            "api_secret", "OeT7FMp5afVS4DY5VGvr_Z9zBAk",
            "secure", true
        ));
    }

    public String uploadFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName));

            String fileDownloadUri = (String) uploadResult.get("url");

            return fileDownloadUri;
        } catch (Exception e) {
            return "não foi possivel fazer o upload do arquivo";
        }

    }
}
