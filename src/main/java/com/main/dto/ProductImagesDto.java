package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ProductImagesDto implements Serializable {
    String id;

    String productId;

    String imagePath;
}