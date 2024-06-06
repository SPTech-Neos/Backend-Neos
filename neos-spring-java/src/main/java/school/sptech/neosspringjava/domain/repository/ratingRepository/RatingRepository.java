package school.sptech.neosspringjava.domain.repository.ratingRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.rating.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
