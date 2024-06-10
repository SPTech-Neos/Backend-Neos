package school.sptech.neosspringjava.service.integracaoImageApi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import school.sptech.neosspringjava.config.IntegracaoimageApiConfig;

public class IntegracaoImageApiServiceTest {

    @Mock
    private IntegracaoimageApiConfig fileStorageApiProperties;

    @Mock
    private Cloudinary cloudinary;

    @InjectMocks
    private IntegracaoImageApiService integracaoImageApiService;

    private Path fileStorageLocation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(fileStorageApiProperties.getUploadDir()).thenReturn("uploads");
        fileStorageLocation = Paths.get(fileStorageApiProperties.getUploadDir()).toAbsolutePath().normalize();
        integracaoImageApiService = new IntegracaoImageApiService(fileStorageApiProperties);
    }
}