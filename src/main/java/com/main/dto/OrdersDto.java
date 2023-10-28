package com.main.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class OrdersDto implements Serializable {
    String id;

    Integer userId;

    Boolean paymentType;

    String orderStatus;

    String discountId;

    String toName;

    String toPhone;

    String toCity;

    String toDistrict;

    String toWard;

    String toAddress;

    Integer weight;

    Integer length;

    Integer width;

    Integer height;

    Integer serviceTypeId;

    Integer serviceId;

    String requiredNote;

    String senderNote;

    Timestamp dateCreated;
}