package com.main.controller.admin;

import com.google.gson.Gson;
import com.main.entity.Orders;
import com.main.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("quan-tri/doanh-thu-nam")
public class ProcessController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping()
    public String doanhThuNam(@RequestParam(name = "year", defaultValue = "2023") int year, Model model) {
        BigDecimal revenue = revenueService.calculateRevenueForYear(year);
        model.addAttribute("year", year);
        model.addAttribute("revenue", revenue);

        List<Object[]> ordersInYear = revenueService.getOrdersByCreatedAt_Year(year);

        Map<Integer, BigDecimal> monthlyProfitMap = new HashMap<>();
        for (Object[] order : ordersInYear) {
            if (order[1] instanceof Orders ordersEntity) {
                LocalDateTime createdAt = ordersEntity.getDateCreated().toLocalDateTime();
                int month = createdAt.getMonthValue();
                BigDecimal totalAmount = BigDecimal.valueOf(((Number) order[0]).doubleValue());

                monthlyProfitMap.merge(month, totalAmount, BigDecimal::add);
            }
        }

        List<BigDecimal> profitData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            BigDecimal profit = monthlyProfitMap.getOrDefault(month, BigDecimal.ZERO);
            profitData.add(profit);
        }

        Gson gson = new Gson();
        String profitDataJson = gson.toJson(profitData);
        model.addAttribute("profitData", profitDataJson);

        model.addAttribute("AverageRevenueByYear", revenueService.calculateAverageRevenue());
        model.addAttribute("topSellingProducts", revenueService.findTopSellingProducts());

        return "views/admin/page/views/doanh-thu-nam";
    }

    @GetMapping("load-bieu-do")
    public ResponseEntity<Map<String, Object>> getRevenueByYear(@RequestParam("year") int year) {
        Map<String, Object> response = new HashMap<>();
        BigDecimal revenue = revenueService.calculateRevenueForYear(year);

        List<Object[]> ordersInYear = revenueService.getOrdersByCreatedAt_Year(year);

        Map<Integer, BigDecimal> monthlyProfitMap = new HashMap<>();
        for (Object[] order : ordersInYear) {
            if (order[1] instanceof Orders ordersEntity) {
                LocalDateTime createdAt = ordersEntity.getDateCreated().toLocalDateTime();
                int month = createdAt.getMonthValue();
                BigDecimal totalAmount = BigDecimal.valueOf(((Number) order[0]).doubleValue());

                monthlyProfitMap.merge(month, totalAmount, BigDecimal::add);
            }
        }

        List<BigDecimal> profitData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            BigDecimal profit = monthlyProfitMap.getOrDefault(month, BigDecimal.ZERO);
            profitData.add(profit);
        }

        Gson gson = new Gson();

        response.put("revenue", revenue);
        response.put("profitData", profitData);
        response.put("topSellingProducts", revenueService.findTopSellingProducts());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ModelAttribute("selectYear")
    public List<Integer> filterOrdersByUniqueYear() {
        return revenueService.findDistinctOrdersByYear();
    }
}
