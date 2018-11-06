package com.cloudtravel.cloudtravelwebservice.Service;

import com.cloudtravel.cloudtravelwebservice.DTO.ScheduleDTO;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleForm;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleUpdateForm;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    List<ScheduleDTO> findCurrentScheduleByUserID(Integer ID);

    List<ScheduleDTO> findScheduleByUserIDAndTime(Integer ID, Date time);

    void createSchedule(Integer userID, ScheduleForm scheduleForm);

    void deleteScheduleByScheduleID(Integer ID);

    void updateSchedule(ScheduleUpdateForm scheduleUpdateForm);

    Integer findUserIDOfSchedule(Integer scheduleID);
}
