package school.sptech.neosspringjava.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.domain.model.Market;

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

    @Query(value = "SELECT DATE(o.date_time) AS period, " +
                   "COUNT(CASE WHEN o.fk_status = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fk_product = p.product_Id " +
                   "JOIN Orders o ON m.fk_order = o.order_Id " +
                   "WHERE p.fk_establishment = :establishmentId " +
                   "AND o.date_time BETWEEN :startDate AND :endDate " +
                   "GROUP BY DATE(o.date_time)",
           nativeQuery = true)
    List<Object[]> findDailyOrderStats(@Param("establishmentId") Integer establishmentId,
                                            @Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT YEARWEEK(o.date_time, 1) AS period, " +
                   "COUNT(CASE WHEN o.fk_status = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fk_product = p.product_Id " +
                   "JOIN Orders o ON m.fk_order = o.order_Id " +
                   "WHERE p.fk_establishment = :establishmentId " +
                   "AND o.date_time BETWEEN :startDate AND :endDate " +
                   "GROUP BY YEARWEEK(o.date_time, 1)",
           nativeQuery = true)
    List<Object[]> findWeeklyOrderStats(@Param("establishmentId") Integer establishmentId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT DATE_FORMAT(o.date_time, '%Y-%m') AS period, " +
                   "COUNT(CASE WHEN o.fk_status = 5 THEN m.market_id END) AS canceledOrders, " +
                   "COUNT(m.market_id) AS totalOrders " +
                   "FROM Market m " +
                   "JOIN Product p ON m.fk_product = p.product_Id " +
                   "JOIN Orders o ON m.fk_order = o.order_Id " +
                   "WHERE p.fk_establishment = :establishmentId " +
                   "AND o.date_time BETWEEN :startDate AND :endDate " +
                   "GROUP BY DATE_FORMAT(o.date_time, '%Y-%m')",
           nativeQuery = true)
    List<Object[]> findMonthlyOrderStats(@Param("establishmentId") Integer establishmentId,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);
}
