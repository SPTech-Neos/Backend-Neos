package school.sptech.neosspringjava.domain.repository.marketRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.market.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {

    @Query("SELECT p.name, SUM(m.quantity) " +
    "FROM Market m " +
    "JOIN m.product p " +
    "WHERE p.establishment.id = :establishmentId " +
    "GROUP BY p.name " +
    "ORDER BY SUM(m.quantity) DESC")
List<Object[]> findAggregatedMarketDataByEstablishment(@Param("establishmentId") Integer establishmentId);


@Query("SELECT p.name, SUM(m.quantity * p.price) " +
       "FROM Market m " +
       "JOIN m.product p " +
       "WHERE p.establishment.id = :establishmentId " +
       "GROUP BY p.name " +
       "ORDER BY SUM(m.quantity * p.price) DESC")
List<Object[]> findTotalSalesValueByProduct(@Param("establishmentId") Integer establishmentId);


}
