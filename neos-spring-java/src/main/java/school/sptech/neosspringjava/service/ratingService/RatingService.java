package school.sptech.neosspringjava.service.ratingService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingRequest;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingResponse;
import school.sptech.neosspringjava.api.mappers.ratingMapper.RatingMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.rating.Rating;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.ratingRepository.RatingRepository;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final EstablishmentRopository establishmentRopository;
    private final ClientRepository clientRepository;

    public RatingResponse createRating(RatingRequest ratingRequest) {

        Establishment establishment = establishmentRopository.findById(ratingRequest.establishment()).orElseThrow(() -> new RuntimeException("Establishment not found"));

        Client client = clientRepository.findById(ratingRequest.client()).orElseThrow(() -> new RuntimeException("Client not found"));
        Rating rating = new Rating();
        rating.setNota(ratingRequest.nota());
        rating.setEstablishment(establishment);
        rating.setClient(client);

        return ratingMapper.toResponse(ratingRepository.save(rating));
    }

    public List<RatingResponse> listAllRatings() {
        return ratingMapper.toResponseList(ratingRepository.findAll());
    }

    public RatingResponse findRatingById(Integer id) {
        return ratingMapper.toResponse(ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found")));

    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }

    public RatingResponse updateRating(Integer id, RatingRequest ratingRequest) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        
        Establishment establishment = establishmentRopository.findById(ratingRequest.establishment()).orElseThrow(() -> new RuntimeException("Establishment not found"));

        Client client = clientRepository.findById(ratingRequest.client()).orElseThrow(() -> new RuntimeException("Client not found"));

        rating.setNota(ratingRequest.nota());
        rating.setEstablishment(establishment);
        rating.setClient(client);

        ratingRepository.save(rating);

        return ratingMapper.toResponse(rating);

    }

    

}
