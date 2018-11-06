package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

import java.util.Date;

@Data
public class MomentsComment {

    private Integer ID;
    private Integer momentsID;
    private Integer userID;
    private Date time;
    private String content;
}
