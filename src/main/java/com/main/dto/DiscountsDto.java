package com.main.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DiscountsDto implements Serializable {

    String id;

    Integer discountCost;

    Integer quantity;

    LocalDateTime startUse;

    LocalDateTime endUse;

    String discountDescription;

    Boolean isActive;
}
