package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.Service;

public record RatingProductResponse(
        Integer id,
        Integer avaliation,
        Product product,
        Client client
) {
}
