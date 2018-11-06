package com.cloudtravel.cloudtravelwebservice.Form;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleUpdateForm {

    private Integer ID;
    private Date time;
    private String memo;
}
