package com.main.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class DiscountsDto implements Serializable {
    String id;

    Integer discountCost;

    Integer quantity;

    Timestamp startUse;

    Timestamp endUse;

    String discountDescription;

    boolean isActive;
}