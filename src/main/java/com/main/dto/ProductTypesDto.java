package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductTypesDto implements Serializable {

    int id;

    int categoryId;

    String productTypeName;

    Boolean isActive;

    List<ProductsDto> products;
}