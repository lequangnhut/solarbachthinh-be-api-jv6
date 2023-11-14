package com.main.controller.admin;

import com.main.entity.Orders;
import com.main.service.OrderItemService;
import com.main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("quan-tri")
public class ConfirmOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("xac-nhan-don-hang")
    public String confirm_order() {
        return "views/admin/page/views/confirm-order-list";
    }

    @GetMapping("api/confirmOrder/{orderId}")
    private ResponseEntity<Orders> confirmOrder(@PathVariable String orderId) {
        Orders order = orderService.findByOrderId(orderId);
        order.setOrderStatus("Đang vận chuyển");
        return ResponseEntity.ok().body(orderService.save(order));
    }

    @GetMapping("api/deliveredOrder/{orderId}")
    private ResponseEntity<Orders> deliveredOrder(@PathVariable String orderId) {
        Orders order = orderService.findByOrderId(orderId);
        order.setOrderStatus("Đã giao hàng");
        order.setPaymentStatus(1);
        return ResponseEntity.ok().body(orderService.save(order));
    }

    @GetMapping("api/cancelOrder/{orderId}")
    private ResponseEntity<Orders> cancelOrder(@PathVariable String orderId) {
        Orders order = orderService.findByOrderId(orderId);
        order.setOrderStatus("Đã huỷ đơn");
        order.setPaymentStatus(2);
        orderService.save(order);
        return ResponseEntity.ok().body(orderService.save(order));
    }

    @GetMapping("api/findAllOrder")
    public ResponseEntity<List<Orders>> findAllOrder() {
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @GetMapping("api/findByOrderId/{orderId}")
    public ResponseEntity<Orders> findByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok().body(orderService.findByOrderId(orderId));
    }

    @GetMapping("api/findOrderItemByOrderId/{orderId}")
    public ResponseEntity<List<Object[]>> findOrderItemByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok().body(orderItemService.findByOrderId(orderId));
    }
}
