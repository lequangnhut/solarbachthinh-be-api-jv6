package com.main.components;

import com.main.service.SaleOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    SaleOffService saleOffService;

    @Scheduled(fixedDelay = 60000)
    public void checkDiscountStatus() {
        saleOffService.updateSalsOffStatus();
        System.out.println("Cập nhật thành công");
    }
}
