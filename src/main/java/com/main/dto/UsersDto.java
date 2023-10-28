package com.main.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UsersDto implements Serializable {
    int id;

    String email;

    String passwords;

    String fullname;

    String phoneNumber;

    Boolean gender;

    Date birth;

    String city;

    String province;

    String ward;

    String address;

    Timestamp dateCreated;

    boolean isAcctive;

    String token;
}