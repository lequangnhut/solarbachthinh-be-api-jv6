package com.main.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class InvoicesDto implements Serializable {
    String id;

    String orderId;

    Integer shipCost;

    Timestamp dateCreated;
}