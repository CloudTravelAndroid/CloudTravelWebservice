package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Moments {

    private Integer ID;
    private Integer userID;
    private Integer universityID;
    private Date time;
    private String content;
}
