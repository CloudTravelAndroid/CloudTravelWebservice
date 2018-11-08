package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDTO {

    private Integer id;
    private String locationName;
    private String locationAddress;
    private Date time;
    private Double latitude;
    private Double longitude;
    private String memo;
}
