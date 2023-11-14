package com.main.dto;

import com.main.entity.Roles;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Data
public class UsersDto implements Serializable {

    int id;

    String email;

    String passwords;

    String fullname;

    String phoneNumber;

    Boolean gender;

    Date birth;

    String ProvinceName;

    String DistrictName;

    String WardName;

    String address;

    Timestamp dateCreated;

    boolean isAcctive;

    Collection<Roles> roles;

    String token;

    Integer totalOrderPrice;

    Integer orderCount;

}