package com.main.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
class HistoryUpdateDto implements Serializable {

    int id;

    int userId;

    String activity;

    Timestamp dateCreated;
}