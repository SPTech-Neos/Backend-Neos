package school.sptech.neosspringjava.domain.repository.ratingRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.rating.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

    @Query("SELECT TRUNCATE(AVG(r.avaliation), 1) media FROM Rating r JOIN r.establishment e WHERE e.id = :id")
    Optional<Double> findMediaByEstablishment(@Param("id") Integer id);

    @Query("SELECT TRUNCATE(AVG(r.avaliation), 1) AS media FROM Rating r JOIN r.establishment e GROUP BY e.id")
    List<Double> findAllMediasByEstablishment();

    @Query("SELECT e, TRUNCATE(AVG(r.avaliation), 1) AS media FROM Rating r JOIN r.establishment e GROUP BY e.id ORDER BY media DESC LIMIT :top")
    List<Establishment> findBestRatedsByTop(@Param("top") Integer top);
    @Query("SELECT TRUNCATE(AVG(r.avaliation), 1) AS media FROM Rating r JOIN r.establishment e GROUP BY e.id ORDER BY media DESC LIMIT :top")
    List<Double> findBestMediasByTop(@Param("top") Integer top);
}
