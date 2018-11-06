package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

@Data
public class Location {

    private Integer ID;
    private String UID;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}
