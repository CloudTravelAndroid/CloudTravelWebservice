package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

@Data
public class User {

    private Integer ID;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private Integer universityID;
}
