package com.main.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentDto {

    String fullname;

    String phoneNumber;

    String provinceName;

    String districtName;

    String wardName;

    String address;
}
