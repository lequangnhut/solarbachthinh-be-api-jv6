package com.main.service;

import com.main.dto.RevenueDto;
import com.main.entity.Orders;

import java.math.BigDecimal;
import java.util.List;

public interface RevenueService {
    BigDecimal calculateRevenueForYear(int year);

    List<Object[]> getOrdersByCreatedAt_Year(int year);

    List<Integer> findDistinctOrdersByYear();

    List<Integer> calculateAverageRevenue();

    List<Object[]> findTopSellingProducts();

    List<RevenueDto> getRevenueByYear(int year);

}
