package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.ratingDto.*;
import school.sptech.neosspringjava.api.mappers.RatingMapper;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.Rating;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.ProductRepository;
import school.sptech.neosspringjava.domain.repository.RatingRepository;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;

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

        Establishment e = establishmentService.findById(ratingRequest.establishment());
        rating.setEstablishment(e);

        Client client = clientService.findById(ratingRequest.client());
        rating.setClient(client);

        rating.setAvaliation(ratingRequest.avaliation());

        return ratingRepository.save(rating);
    }

    public Rating avaliateService(RatingServiceRequest ratingRequest) {
        Rating rating = new Rating();

            school.sptech.neosspringjava.domain.model.Service service = serviceRepository.findById(ratingRequest.service())
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
        
        Establishment e = establishmentService.findById(ratingRequest.establishment());

        Client client = clientService.findById(ratingRequest.client());

        rating.setAvaliation(ratingRequest.avaliation());
        rating.setEstablishment(e);
        rating.setClient(client);

        ratingRepository.save(rating);

        return ratingMapper.toResponse(ratingRepository.save(rating));

    }

//    public Double findAvgEstablishmentRating(Integer id){
//        EstablishmentResponse eDto = establishmentService.findById(id);
//
//        Optional<Double> optMedia = ratingRepository.findMediaByEstablishment(EstablishmentMapper.toEstablishment(eDto));
//
//        return optMedia.get();
//    }

}
