package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Schedule {

    private Integer ID;
    private Date time;
    private Integer locationID;
    private Integer userID;
    private String memo;
}
