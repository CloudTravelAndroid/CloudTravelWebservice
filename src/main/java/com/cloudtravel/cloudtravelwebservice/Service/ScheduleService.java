package com.cloudtravel.cloudtravelwebservice.Service;

import com.cloudtravel.cloudtravelwebservice.DTO.ScheduleDTO;

public interface ScheduleService {

    ScheduleDTO getScheduleInfoByUserID(Integer userID);

    Integer createSchedule(Integer userID);

    int deleteScheduleByID(Integer ID);



}
