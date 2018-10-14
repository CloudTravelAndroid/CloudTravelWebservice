package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

@Data
public class User {

    private Integer ID;
    private String name;
    private String password;
    private String email;
    private String phone_number;
    private Integer universityID;
}
