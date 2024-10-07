package school.sptech.neosspringjava.api.mappers;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.ratingDto.*;
import school.sptech.neosspringjava.domain.model.Rating;

@Component
public class RatingMapper {

    public static RatingResponse toResponse(Rating rating) {
        return new RatingResponse(
                rating.getId(),
                rating.getAvaliation(),
                rating.getEstablishment(),
                rating.getClient(),
                rating.getEmployee(),
                rating.getService(),
                rating.getProduct()
        );
    }

    public static RatingServiceResponse toResponseService(Rating rating) {
        return new RatingServiceResponse(
                rating.getId(),
                rating.getAvaliation(),
                rating.getService(),
                rating.getClient()
        );
    }

    public static RatingProductResponse toResponseProduct(Rating rating) {
        return new RatingProductResponse(
                rating.getId(),
                rating.getAvaliation(),
                rating.getProduct(),
                rating.getClient()
        );
    }

    public static RatingEmployeeResponse toResponseEmployee(Rating rating) {
        return new RatingEmployeeResponse(
                rating.getId(),
                rating.getAvaliation(),
                rating.getEmployee(),
                rating.getClient()
        );
    }

    public static RatingEstablishmentResponse toResponseEstablishment(Rating rating) {
        return new RatingEstablishmentResponse(
                rating.getId(),
                rating.getAvaliation(),
                rating.getEstablishment(),
                rating.getClient()
        );
    }

    public static List<RatingResponse> toResponseList(List<Rating> ratings) {
        return ratings.stream().map(RatingMapper::toResponse).collect(Collectors.toList());
    }

    public static List<RatingEstablishmentResponse> toResponseEstablishmentList(List<Rating> ratings) {
        return ratings.stream().map(RatingMapper::toResponseEstablishment).collect(Collectors.toList());
    }

    public static List<RatingEmployeeResponse> toResponseEmployeeList(List<Rating> ratings) {
        return ratings.stream().map(RatingMapper::toResponseEmployee).collect(Collectors.toList());
    }

    public static List<RatingServiceResponse> toResponseServiceList(List<Rating> ratings) {
        return ratings.stream().map(RatingMapper::toResponseService).collect(Collectors.toList());
    }

    public static List<RatingProductResponse> toResponseProductList(List<Rating> ratings) {
        return ratings.stream().map(RatingMapper::toResponseProduct).collect(Collectors.toList());
    }

    

}
