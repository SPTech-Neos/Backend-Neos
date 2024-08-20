package school.sptech.neosspringjava.service.ratingService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingRequest;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingResponse;
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

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final EstablishmentRepository establishmentRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public RatingResponse save(RatingRequest ratingRequest) {
          Rating rating = new Rating();
        if (ratingRequest.establishment() != null ) {

            Establishment establishment = establishmentRepository.findById(ratingRequest.establishment())
                .orElseThrow(() -> new RuntimeException("Establishment not found"));
                  rating.setEstablishment(establishment);

        }else if(ratingRequest.service() != null){

            school.sptech.neosspringjava.domain.model.service.Service service = serviceRepository.findById(ratingRequest.service())
                .orElseThrow(() -> new RuntimeException("Service not found"));
            rating.setService(service);

        } else if(ratingRequest.product() != null){

            Product product = productRepository.findById(ratingRequest.product())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            rating.setProduct(product);

        }else if(ratingRequest.employee() != null){

            Employee employee = employeeRepository.findById(ratingRequest.employee())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            rating.setEmployee(employee);

        }

        if(ratingRequest.client() != null){
            Client client = clientRepository.findById(ratingRequest.client())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            rating.setClient(client);

        }

        rating.setAvaliation(ratingRequest.avaliation());

        return ratingMapper.toResponse(ratingRepository.save(rating));
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
        
        Establishment establishment = establishmentRepository.findById(ratingRequest.establishment()).orElseThrow(() -> new RuntimeException("Establishment not found"));

        Client client = clientRepository.findById(ratingRequest.client()).orElseThrow(() -> new RuntimeException("Client not found"));

        rating.setAvaliation(ratingRequest.avaliation());
        rating.setEstablishment(establishment);
        rating.setClient(client);

        ratingRepository.save(rating);

        return ratingMapper.toResponse(rating);

    }

    

}
