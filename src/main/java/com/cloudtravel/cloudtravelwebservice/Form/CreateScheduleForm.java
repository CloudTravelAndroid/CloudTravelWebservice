package com.cloudtravel.cloudtravelwebservice.Form;

import lombok.Data;

@Data
public class CreateScheduleForm {

    private Integer LocationID;
    private Integer userID;
    private String memo;
}
