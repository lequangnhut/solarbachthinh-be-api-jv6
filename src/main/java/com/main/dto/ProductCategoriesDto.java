package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ProductCategoriesDto implements Serializable {
    int id;

    String categoryName;

    byte[] categoryImage;

    Boolean isActive;
}