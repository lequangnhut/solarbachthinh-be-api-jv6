package com.main.repository;

import com.main.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findAllByOrderByDateCreatedDesc();

    List<Orders> findByUserIdOrderByDateCreatedDesc(int userId);

    @Query("SELECT CalculateTotalOrderPrice(:userId)")
    BigDecimal sumOrdersPriceByAccountIdProfile(@Param("userId") Integer userId);

    @Query("SELECT COUNT(o) FROM Orders o JOIN o.usersByUserId a WHERE a.id = :userId")
    BigDecimal countOrdersByAccountIdProfile(@Param("userId") Integer userId);

    // doanh thu năm

    @Query("SELECT DISTINCT YEAR(o.dateCreated) FROM Orders o")
    List<Integer> findDistinctOrdersByYear();

    @Query("SELECT SUM(ct.price * ct.quantity + o.orderShipCost) as totalAmount, o" +
            " FROM Orders o JOIN o.orderItemsById ct" +
            " WHERE EXTRACT(YEAR FROM o.dateCreated) = :year and o.paymentStatus = 1" +
            " GROUP BY o")
    List<Object[]> getOrdersByCreatedAt_Year(@Param("year") int year);


    @Query("SELECT SUM(ct.price * ct.quantity + o.orderShipCost) FROM Orders o " +
            "JOIN o.orderItemsById ct " +
            "JOIN o.usersByUserId a " +
            "WHERE EXTRACT(YEAR FROM o.dateCreated) = :year and o.paymentStatus = 1")
    BigDecimal calculateRevenueForYear(@Param("year") int year);

    @Query("SELECT AVG(ct.price * ct.quantity + o.orderShipCost) FROM Orders o " +
            "JOIN o.orderItemsById ct " +
            "JOIN o.usersByUserId a " +
            "WHERE o.paymentStatus = 1 ")
    List<Integer> calculateAverageRevenue();

    @Query("SELECT p.id, b.brandName, p.productName, SUM(ot.quantity) AS total_sold, SUM(ot.price * ot.quantity + o.orderShipCost) AS total_revenue FROM Orders o" +
            " JOIN o.orderItemsById ot" +
            " JOIN ot.productsByProductId p" +
            " JOIN p.productBrandsByProductBrandId b" +
            " GROUP BY p.id" +
            " ORDER BY total_sold DESC" +
            " LIMIT 5")
    List<Object[]> findTopSellingProducts();

    @Query("SELECT MONTH(o.dateCreated), SUM(o.orderShipCost) FROM Orders o WHERE YEAR(o.dateCreated) = :year GROUP BY MONTH(o.dateCreated)")
    List<Object[]> getRevenueByYear(@Param("year") int year);
}