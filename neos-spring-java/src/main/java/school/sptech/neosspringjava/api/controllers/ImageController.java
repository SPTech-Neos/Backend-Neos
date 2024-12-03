package school.sptech.neosspringjava.api.controllers;

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
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.filesDto.FileResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageClientResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageEmployeeResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageEstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageProductResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.imageDto.ImageServiceResponse;
import school.sptech.neosspringjava.service.CloudinaryService;
import school.sptech.neosspringjava.service.ImageService;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class ImageController {
   

    private final ImageService cloudinaryService;

    @PostMapping("/img-upload/client")
    public ResponseEntity<ImageClientResponse> uploadFileClient(@RequestParam("file") MultipartFile file,   @RequestParam("entityId") Integer entityId) {
        try {
            ImageClientResponse fileResponse = (ImageClientResponse) cloudinaryService.uploadImg(file, "client", entityId);
             return ResponseEntity.status(200).body(fileResponse);
        } catch (IOException e) {
            return null;

        }
    }

    @PostMapping("/img-upload/product")
    public ResponseEntity<ImageProductResponse> uploadFileProduct(@RequestParam("file") MultipartFile file,   @RequestParam("entityId") Integer entityId) {
        try {
            ImageProductResponse fileResponse = (ImageProductResponse) cloudinaryService.uploadImg(file, "product", entityId);
             return ResponseEntity.status(200).body(fileResponse);
        } catch (IOException e) {
            return null;

        }
    }

    @PostMapping("/img-upload/service")
    public ResponseEntity<ImageServiceResponse> uploadFileService(@RequestParam("file") MultipartFile file,   @RequestParam("entityId") Integer entityId) {
        try {
            ImageServiceResponse fileResponse = (ImageServiceResponse) cloudinaryService.uploadImg(file, "service", entityId);
             return ResponseEntity.status(200).body(fileResponse);
        } catch (IOException e) {
            return null;

        }
    }

    @PostMapping("/img-upload/employee")
    public ResponseEntity<ImageEmployeeResponse> uploadFileEmployee(@RequestParam("file") MultipartFile file,   @RequestParam("entityId") Integer entityId) {
        try {
            ImageEmployeeResponse fileResponse = (ImageEmployeeResponse) cloudinaryService.uploadImg(file, "employee", entityId);
             return ResponseEntity.status(200).body(fileResponse);
        } catch (IOException e) {
            return null;
        }
    }

    @PostMapping("/img-upload/establishment")
    public ResponseEntity<ImageEstablishmentResponse> uploadFileEstablishment(@RequestParam("file") MultipartFile file,   @RequestParam("entityId") Integer entityId) {
        try {
            ImageEstablishmentResponse fileResponse = (ImageEstablishmentResponse) cloudinaryService.uploadImg(file, "establishment", entityId);
            return ResponseEntity.status(200).body(fileResponse);
        } catch (IOException e) {
            return null;

        }
    }


}