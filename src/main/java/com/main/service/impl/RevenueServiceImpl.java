package com.main.service.impl;

import com.main.dto.RevenueDto;
import com.main.entity.Orders;
import com.main.repository.OrderRepository;
import com.main.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private OrderRepository orderRepository;

    public BigDecimal calculateRevenueForYear(int year) {
        return orderRepository.calculateRevenueForYear(year);
    }

    @Override
    public List<Object[]> getOrdersByCreatedAt_Year(int year) {
        return orderRepository.getOrdersByCreatedAt_Year(year);
    }

    @Override
    public List<Integer> findDistinctOrdersByYear() {
        return orderRepository.findDistinctOrdersByYear();
    }

    @Override
    public List<Integer> calculateAverageRevenue() {
        return orderRepository.calculateAverageRevenue();
    }

    @Override
    public List<Object[]> findTopSellingProducts() {
        return orderRepository.findTopSellingProducts();
    }

    @Override
    public List<RevenueDto> getRevenueByYear(int year) {
        List<Object[]> result = orderRepository.getRevenueByYear(year);
        List<RevenueDto> revenueList = new ArrayList<>();

        for (Object[] row : result) {
            int month = (int) row[0];
            BigDecimal revenue = (BigDecimal) row[1];
            revenueList.add(new RevenueDto(month, revenue));
        }

        return revenueList;
    }
}
