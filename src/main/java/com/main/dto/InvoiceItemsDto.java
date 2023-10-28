package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class InvoiceItemsDto implements Serializable {
    String id;

    String invoiceId;
}