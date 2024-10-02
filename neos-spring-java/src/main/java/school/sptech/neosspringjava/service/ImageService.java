package school.sptech.neosspringjava.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.filesDto.FileResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.MesageResponse;
import school.sptech.neosspringjava.api.mappers.ImageMapper;
import school.sptech.neosspringjava.domain.model.Image;
import school.sptech.neosspringjava.domain.repository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.ImageRepository;
import school.sptech.neosspringjava.domain.repository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;
import school.sptech.neosspringjava.api.dtos.filesDto.ImageResponse;
import school.sptech.neosspringjava.service.client.ClientService;
import school.sptech.neosspringjava.service.employee.EmployeeService;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static final List<String> extencoesLiberadas = List.of("jpg", "png", "gif", "jpeg");

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ClientRepository clientService;
    private final EmployeeRepository employeeService;
    private final EstablishmentRepository establishmentService;
    private final ProductRepository productService;
    private final ServiceRepository serviceService;

    public Object uploadImg(MultipartFile file, String entity, Integer entityId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Double tamanho = file.getSize() / 1024.0;
        tamanho = Math.round(tamanho * 100.0) / 100.0;
        String extencao = fileName.substring(fileName.lastIndexOf(".") + 1);

        try {


            Image img = CloudinaryService.uploadImg(file);
System.out.println("img: "+img.getPath());
            switch (entity) {
                case "client":
                    img.setClient(clientService.findById(entityId).orElse(null));
                    break;
            
                case "product":
                    img.setProduct(productService.findById(entityId).orElse(null));
                    break;

                case "service":

                    img.setService(serviceService.findById(entityId).orElse(null));
                    break;

                case "employee":
                    img.setEmployee(employeeService.findById(entityId).orElse(null));
                    break;

                case "establishment":
                    img.setEstablishment(establishmentService.findById(entityId).orElse(null));
                    break;

                    
                default:
                    break;
            }

            imageRepository.save(img);

            return imageMapper.toImageResponse(img, entity);


        } catch (Exception e) {
            return null;

        }

    }

}
