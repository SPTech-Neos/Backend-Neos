package school.sptech.neosspringjava.api.mappers.ratingMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.ratingDto.RatingRequest;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingResponse;
import school.sptech.neosspringjava.domain.model.rating.Rating;

@Component
public class RatingMapper {

    public RatingResponse toResponse(Rating rating) {
        return new RatingResponse(rating.getId(), rating.getNota(), rating.getEstablishment(), rating.getClient());
    }

    public List<RatingResponse> toResponseList(List<Rating> ratings) {
        return ratings.stream().map(this::toResponse).collect(Collectors.toList());
    }

    

}
