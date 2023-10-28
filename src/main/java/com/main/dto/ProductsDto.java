package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ProductsDto implements Serializable {
    String id;

    Integer productTypeId;

    String productBrandId;

    String productName;

    Integer price;

    Integer quantity;

    String descriptions;

    String templateDescription;

    String powers;

    Integer warranty;

    Integer saleOff;

    String isStatusDelete;

    Timestamp dateCreated;
}