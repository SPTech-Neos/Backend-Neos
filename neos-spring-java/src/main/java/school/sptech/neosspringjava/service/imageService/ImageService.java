package school.sptech.neosspringjava.service.imageService;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.filesDto.FileResponse;
import school.sptech.neosspringjava.api.dtos.filesDto.MesageResponse;
import school.sptech.neosspringjava.api.mappers.imageMapper.ImageMapper;
import school.sptech.neosspringjava.domain.model.image.Image;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.imageRepository.ImageRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.api.dtos.filesDto.ImageResponse;
import school.sptech.neosspringjava.service.EmployeeServService.EmployeeServService;
import school.sptech.neosspringjava.service.apiServices.CloudinaryService;
import school.sptech.neosspringjava.service.client.ClientService;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.serviceService.ServiceService;

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
