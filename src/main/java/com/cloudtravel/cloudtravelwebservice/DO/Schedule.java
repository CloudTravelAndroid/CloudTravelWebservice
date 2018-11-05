package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Schedule {

    private Integer scheduleId;

    private Timestamp date;

    private Integer locationId;

    private String memo;

}
