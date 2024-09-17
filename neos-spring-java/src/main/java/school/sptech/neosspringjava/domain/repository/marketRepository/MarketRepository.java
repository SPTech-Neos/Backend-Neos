package school.sptech.neosspringjava.domain.repository.marketRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.domain.model.market.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {

    @Query("SELECT p.name, COALESCE(SUM(m.quantity),0) " +
            "FROM Market m " +
            "JOIN m.product p " +
            "WHERE p.establishment.id = :establishmentId " +
            "GROUP BY p.name " +
            "ORDER BY SUM(m.quantity) DESC")
    List<Object[]> findAggregatedMarketDataByEstablishment(@Param("establishmentId") Integer establishmentId);

    @Query("SELECT p.name, COALESCE(SUM(m.quantity * p.price),0) " +
            "FROM Market m " +
            "JOIN m.product p " +
            "WHERE p.establishment.id = :establishmentId " +
            "GROUP BY p.name " +
            "ORDER BY SUM(m.quantity * p.price) DESC")
    List<Object[]> findTotalSalesValueByProduct(@Param("establishmentId") Integer establishmentId);

    @Query("SELECT COUNT(m.id) " +
            "FROM Market m " +
            "JOIN m.product p " +
            "JOIN m.order o " +
            "WHERE p.establishment.id = :establishmentId " +
            "AND o.status.id = 5 " +
            "AND o.dateTime BETWEEN :startDate AND :endDate")
    Integer countMarketsByEstablishmentAndOrderStatusCanceled(@Param("establishmentId") Integer establishmentId,
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(m.id) " +
            "FROM Market m " +
            "JOIN m.product p " +
            "JOIN m.order o " +
            "WHERE p.establishment.id = :establishmentId " +
            "AND o.dateTime BETWEEN :startDate AND :endDate")
    Integer countMarketsByEstablishmentAndOrderStatus(
            @Param("establishmentId") Integer establishmentId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT DATE(o.dateTime) AS period, " +
                   "COUNT(CASE WHEN o.fkstatus = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fkproduct = p.product_Id " +
                   "JOIN Orders o ON m.fkOrder = o.order_Id " +
                   "WHERE p.fkEstablishment = :establishmentId " +
                   "AND o.dateTime BETWEEN :startDate AND :endDate " +
                   "GROUP BY DATE(o.dateTime)",
           nativeQuery = true)
    List<Object[]> findDailyOrderStats(@Param("establishmentId") Integer establishmentId,
                                            @Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT YEARWEEK(o.dateTime, 1) AS period, " +
                   "COUNT(CASE WHEN o.fkstatus = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fkproduct = p.product_Id " +
                   "JOIN Orders o ON m.fkOrder = o.order_Id " +
                   "WHERE p.fkEstablishment = :establishmentId " +
                   "AND o.dateTime BETWEEN :startDate AND :endDate " +
                   "GROUP BY YEARWEEK(o.dateTime, 1)",
           nativeQuery = true)
    List<Object[]> findWeeklyOrderStats(@Param("establishmentId") Integer establishmentId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT DATE_FORMAT(o.dateTime, '%Y-%m') AS period, " +
                   "COUNT(CASE WHEN o.fkstatus = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fkproduct = p.product_Id " +
                   "JOIN Orders o ON m.fkOrder = o.order_Id " +
                   "WHERE p.fkEstablishment = :establishmentId " +
                   "AND o.dateTime BETWEEN :startDate AND :endDate " +
                   "GROUP BY DATE_FORMAT(o.dateTime, '%Y-%m')",
           nativeQuery = true)
    List<Object[]> findMonthlyOrderStats(@Param("establishmentId") Integer establishmentId,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);
}
