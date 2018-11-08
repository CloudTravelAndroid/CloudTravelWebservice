package com.cloudtravel.cloudtravelwebservice.Form;

import lombok.Data;

@Data
public class ScheduleForm {

    private String name;
    private String address;
    private String uid;
    private String time;
    private String latitude;
    private String longitude;
    private String memo;
}
