package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ProductAttributesDto implements Serializable {
    int id;

    String productId;

    String attributeName;

    String attributeValue;
}