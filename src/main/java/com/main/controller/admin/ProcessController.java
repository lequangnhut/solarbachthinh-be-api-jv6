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

    // Phương thức chính để hiển thị trang doanh thu năm
    @GetMapping()
    public String doanhThuNam(@RequestParam(name = "year", defaultValue = "2023") int year, Model model) {
        double revenue = getSafeRevenue(year);
        double revenueLastYear = getSafeRevenue(year - 1);
        updateModelForRevenueComparison(model, revenue, revenueLastYear);

        model.addAttribute("year", year);
        model.addAttribute("revenue", revenue);

        List<Object[]> ordersInYear = revenueService.getOrdersByCreatedAt_Year(year);
        model.addAttribute("profitData", getMonthlyProfitData(ordersInYear));

        model.addAttribute("percent", revenueService.calculateAverageRevenue());
        model.addAttribute("AverageRevenueByYear", revenueService.calculateAverageRevenue());
        model.addAttribute("topSellingProducts", revenueService.findTopSellingProducts(year));

        return "views/admin/page/views/doanh-thu-nam";
    }

    // Phương thức phụ trợ để lấy doanh thu an toàn
    private double getSafeRevenue(int year) {
        try {
            return revenueService.calculateRevenueForYear(year);
        } catch (Exception e) {
            return 0;
        }
    }

    // Phương thức phụ trợ để cập nhật model với dữ liệu so sánh doanh thu
    private void updateModelForRevenueComparison(Model model, double revenue, double revenueLastYear) {
        if (revenueLastYear == 0) {
            model.addAttribute("percentCompareToLastYear", "N/A");
            model.addAttribute("muiTen", "N/A"); // Hoặc bỏ qua
        } else {
            String muiTen = (revenue >= revenueLastYear) ? "ti-arrow-up-left text-success" : "ti-arrow-down-right text-danger";

            model.addAttribute("muiTen", muiTen);
            model.addAttribute("percentCompareToLastYear", calculatePercentageChange(revenue, revenueLastYear));
        }
    }


    // Phương thức phụ trợ để tính toán phần trăm thay đổi
    private Double calculatePercentageChange(double newValue, double oldValue) {
        if (oldValue == 0) {
            return null;
        }
        return ((newValue - oldValue) / oldValue) * 100;
    }


    // Phương thức phụ trợ để lấy dữ liệu lợi nhuận hàng tháng
    private List<BigDecimal> getMonthlyProfitData(List<Object[]> ordersInYear) {
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
        return profitData;
    }

    // Phương thức để tải biểu đồ doanh thu theo năm
    @GetMapping("load-bieu-do")
    public ResponseEntity<Map<String, Object>> getRevenueByYear(@RequestParam("year") int year) {
        Map<String, Object> response = new HashMap<>();

        double revenue = getSafeRevenue(year);
        double revenueLastYear = getSafeRevenue(year - 1);

        // Cập nhật response thay vì Model
        updateResponseForRevenueComparison(response, revenue, revenueLastYear);

        try {
            List<Object[]> ordersInYear = revenueService.getOrdersByCreatedAt_Year(year);
            List<BigDecimal> profitData = getMonthlyProfitData(ordersInYear);
            response.put("profitData", profitData);

        } catch (Exception e) {
            response.put("profitData", generateEmptyProfitData());
        }

        response.put("revenue", revenue);
        response.put("topSellingProducts", revenueService.findTopSellingProducts(year));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Phương thức mới để cập nhật Map
    private void updateResponseForRevenueComparison(Map<String, Object> response, double revenue, double revenueLastYear) {
        if (revenueLastYear == 0) {
            response.put("percentCompareToLastYear", "N/A");
            response.put("muiTen", "N/A"); // Hoặc bỏ qua
        } else {
            double percent = calculatePercentageChange(revenue, revenueLastYear);
            String muiTen = (revenue >= revenueLastYear) ? "ti-arrow-up-left text-success" : "ti-arrow-down-right text-danger";

            response.put("muiTen", muiTen);
            response.put("percentCompareToLastYear", percent);
        }
    }


    // Phương thức phụ trợ để tạo dữ liệu lợi nhuận rỗng
    private List<BigDecimal> generateEmptyProfitData() {
        List<BigDecimal> profitData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            profitData.add(BigDecimal.ZERO);
        }
        return profitData;
    }

    // Phương thức để lấy danh sách các năm có đơn đặt hàng
    @ModelAttribute("selectYear")
    public List<Integer> filterOrdersByUniqueYear() {
        return revenueService.findDistinctOrdersByYear();
    }
}
