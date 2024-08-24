package school.sptech.neosspringjava.service.ratingService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.ratingDto.*;
import school.sptech.neosspringjava.api.mappers.ratingMapper.RatingMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.rating.Rating;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.ratingRepository.RatingRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.service.client.ClientService;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final EstablishmentService establishmentService;
    private final ClientService clientService;
    private final ServiceRepository serviceRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public Rating avaliateEstablishment(RatingEstablishmentRequest ratingRequest) {
        Rating rating = new Rating();

        Establishment establishment = establishmentService.findById(ratingRequest.establishment());
        rating.setEstablishment(establishment);

        Client client = clientService.findById(ratingRequest.client());
        rating.setClient(client);

        rating.setAvaliation(ratingRequest.avaliation());

        return ratingRepository.save(rating);
    }

    public Rating avaliateService(RatingServiceRequest ratingRequest) {
        Rating rating = new Rating();

            school.sptech.neosspringjava.domain.model.service.Service service = serviceRepository.findById(ratingRequest.service())
                    .orElseThrow(() -> new RuntimeException("Service not found"));
            rating.setService(service);

        Client client = clientService.findById(ratingRequest.client());
        rating.setClient(client);
        rating.setAvaliation(ratingRequest.avaliation());

        return ratingRepository.save(rating);
    }

    public Rating avaliateProduct(RatingProductRequest ratingRequest) {
        Rating rating = new Rating();

        Product product = productRepository.findById(ratingRequest.product())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        rating.setProduct(product);


        Client client = clientService.findById(ratingRequest.client());
        rating.setClient(client);
        rating.setAvaliation(ratingRequest.avaliation());

        return ratingRepository.save(rating);

    }

    public Rating avaliateEmployee(RatingEmployeeRequest ratingRequest) {
        Rating rating = new Rating();

        Employee employee = employeeRepository.findById(ratingRequest.employee()).
                orElseThrow(
                        () -> new RuntimeException("Funcionário não encontrado")
                );

        rating.setEmployee(employee);

        Client client = clientService.findById(ratingRequest.client());
        rating.setClient(client);
        rating.setAvaliation(ratingRequest.avaliation());

        return ratingRepository.save(rating);
    }

    public List<RatingResponse> listAllRatings() {
        return ratingMapper.toResponseList(ratingRepository.findAll());
    }

    public RatingResponse findRatingById(Integer id) {
       try {
            Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
            return ratingMapper.toResponse(rating);
        } catch (Exception e) {
            throw new RuntimeException("Rating not found");
        }

    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }

    public RatingResponse updateRating(Integer id, RatingRequest ratingRequest) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        
        Establishment establishment = establishmentService.findById(ratingRequest.establishment());

        Client client = clientService.findById(ratingRequest.client());

        rating.setAvaliation(ratingRequest.avaliation());
        rating.setEstablishment(establishment);
        rating.setClient(client);

        ratingRepository.save(rating);

        return ratingMapper.toResponse(ratingRepository.save(rating));

    }

//    public Rating findAvgEstablishmentRating(Integer id){
//        Rating r = ratingRepository.getMediaRatingByEstablishment(id);
//
//        System.out.println(r);
//
//        return r;
//    }

}
