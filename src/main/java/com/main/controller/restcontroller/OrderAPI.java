package com.main.controller.restcontroller;

import com.main.dto.OrdersDto;
import com.main.dto.UserPaymentDto;
import com.main.entity.Carts;
import com.main.entity.OrderItems;
import com.main.entity.Orders;
import com.main.entity.Users;
import com.main.service.CartService;
import com.main.service.OrderItemService;
import com.main.service.OrderService;
import com.main.utils.EntityDtoUtils;
import com.main.utils.RandomUtils;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("api")
public class OrderAPI {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    HttpSession session;

    @PostMapping(value = "order/create-order", consumes = {"application/json;charset=UTF-8"})
    private void createOrder(@RequestBody OrdersDto ordersDto) {
        String orderId = RandomUtils.RandomToken(15);
        BigDecimal price = BigDecimal.valueOf(ordersDto.getTotal());
        String discountId = ordersDto.getDiscountId();
        boolean paymentType = "COD".equals(ordersDto.getPaymentMethod());

        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);

        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(users.getId());
        if (StringUtils.isNotEmpty(discountId)) {
            orders.setDiscountId(discountId);
        }
        orders.setOrderShipCost(BigDecimal.valueOf(ordersDto.getShippingFee()));
        orders.setPaymentStatus(paymentType);
        orders.setPaymentType(paymentType);
        orders.setOrderStatus("Đã đặt hàng");
        orders.setOrderShipCost(price);

        UserPaymentDto userPayment = ordersDto.getUser_payment();
        if (userPayment != null) {
            orders.setToName(userPayment.getFullname());
            orders.setToPhone(userPayment.getPhoneNumber());
            orders.setToProvince(userPayment.getProvinceName());
            orders.setToDistrict(userPayment.getDistrictName());
            orders.setToWard(userPayment.getWardName());
            orders.setToAddress(userPayment.getAddress());
        }

        orders.setOrderNote(ordersDto.getNoted());
        orders.setDateCreated(new Timestamp(System.currentTimeMillis()));

        orderService.save(orders);

        // tạo ra đơn hàng mới sau khi save đơn hàng
        Object[] cartsList = ordersDto.getProductCartDto().getCartsList();
        createOrderItem(orderId, cartsList, price);

        // xoá giỏ hàng
        deleteCart(cartsList);
    }

    private void createOrderItem(String orderId, Object[] cartsList, BigDecimal price) {
        for (Object cart : cartsList) {
            if (cart instanceof LinkedHashMap) {
                Carts carts = EntityDtoUtils.convertToEntity(cart, Carts.class);
                OrderItems orderItems = new OrderItems();

                orderItems.setOrderId(orderId);
                orderItems.setPrice(price);
                orderItems.setProductId(carts.getProductId());
                orderItems.setQuantity(carts.getQuantity());
                orderItemService.save(orderItems);
            }
        }
    }

    private void deleteCart(Object[] cartsList) {
        for (Object cart : cartsList) {
            if (cart instanceof LinkedHashMap) {
                Carts carts = EntityDtoUtils.convertToEntity(cart, Carts.class);
                cartService.delete(carts.getId());
            }
        }
    }
}
