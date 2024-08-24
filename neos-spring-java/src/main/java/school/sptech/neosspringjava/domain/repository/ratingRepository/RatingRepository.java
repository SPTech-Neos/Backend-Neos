package school.sptech.neosspringjava.domain.repository.ratingRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.neosspringjava.domain.model.rating.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

//    @Query("select r from Rating r where r.fkEstablishment = :idEstab")
//    Rating getMediaRatingByEstablishment(@Param("idEstab") Integer id);
}
