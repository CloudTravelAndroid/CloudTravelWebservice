package com.cloudtravel.cloudtravelwebservice.Form;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleForm {

    private String locationName;
    private String locationAddress;
    private Date time;
    private Double latitude;
    private Double longitude;
    private String memo;
}
