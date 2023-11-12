package com.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrdersDto implements Serializable {

    private String orderId;

    private String noted;

    private String paymentMethod;

    private Integer paymentStatus;

    private String serviceName;

    private String discountId;

    private Integer shippingFee;

    private double total;

    UserPaymentDto user_payment;

    ProductCartDto productCartDto;
}