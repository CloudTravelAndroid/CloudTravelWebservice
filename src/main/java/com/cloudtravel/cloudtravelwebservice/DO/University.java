package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

@Data
public class University {

    private Integer ID;
    private String name;
    private Integer provinceID;
    private String website;
    private String city;
    private String address;
    private String openTime;
    private String description;
}
