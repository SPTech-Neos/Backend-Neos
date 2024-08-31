package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;

public record RatingProductResponse(
        Integer id,
        Integer avaliation,
        Product product,
        Client client
) {
}
