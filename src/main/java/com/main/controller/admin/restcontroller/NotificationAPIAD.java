package com.main.controller.admin.restcontroller;

import com.main.entity.NotificationOrder;
import com.main.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/quan-tri")
public class NotificationAPIAD {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/findAll")
    private List<NotificationOrder> findAll() {
        return notificationService.findAll();
    }

    @PutMapping("/notification/updateIsSeen/{id}/{isSeen}")
    private void updateIsSeen(@PathVariable int id, @PathVariable boolean isSeen) {
        NotificationOrder notOrder = notificationService.findById(id);
        notOrder.setIsSeen(isSeen);
        notificationService.save(notOrder);
    }

    @GetMapping("/notification/deleteNoted/{id}")
    private void deleteIsSeen(@PathVariable int id) {
        NotificationOrder notOrder = notificationService.findById(id);
        notificationService.delete(notOrder);
    }
}
