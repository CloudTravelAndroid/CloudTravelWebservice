package com.cloudtravel.cloudtravelwebservice.Service.Impl;

import com.cloudtravel.cloudtravelwebservice.DO.Location;
import com.cloudtravel.cloudtravelwebservice.DO.Schedule;
import com.cloudtravel.cloudtravelwebservice.DTO.ScheduleDTO;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleForm;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleUpdateForm;
import com.cloudtravel.cloudtravelwebservice.Mapper.LocationMapper;
import com.cloudtravel.cloudtravelwebservice.Mapper.ScheduleMapper;
import com.cloudtravel.cloudtravelwebservice.Service.ScheduleService;
import com.cloudtravel.cloudtravelwebservice.Util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleDTO> findCurrentScheduleByUserID(Integer ID) {
        List<Schedule> schedules = scheduleMapper.selectCurrentScheduleByUserID(ID);
        return toScheduleDTO(schedules);
    }

    @Override
    public List<ScheduleDTO> findScheduleByUserIDAndTime(Integer ID, Date time) {
        List<Schedule> schedules = scheduleMapper.selectScheduleByUserIDAndTime(ID, time);
        return toScheduleDTO(schedules);
    }

    @Override
    public void createSchedule(Integer userID, ScheduleForm scheduleForm) {
        Location location = locationMapper.selectLocationByUID(scheduleForm.getUID());
        if (location == null) {
            location = new Location();
            BeanUtils.copyProperties(scheduleForm, location);
            location.setLatitude(Double.valueOf(scheduleForm.getLatitude()));
            location.setLongitude(Double.valueOf(scheduleForm.getLongitude()));
            locationMapper.insertLocation(location);
        }
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleForm, schedule);
        schedule.setLocationID(location.getID());
        Date time = DateUtil.str2Date(scheduleForm.getTime(), DateUtil.FORMAT_yMdHMS);
        schedule.setTime(time);
        schedule.setUserID(userID);
        scheduleMapper.insertSchedule(schedule);
    }

    @Override
    public void deleteScheduleByScheduleID(Integer ID) {
        scheduleMapper.deleteScheduleByScheduleID(ID);
    }

    @Override
    public void updateSchedule(ScheduleUpdateForm scheduleUpdateForm) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleUpdateForm, schedule);
        Date time = DateUtil.str2Date(scheduleUpdateForm.getTime(), DateUtil.FORMAT_yMdHMS);
        schedule.setTime(time);
        scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public Integer findUserIDOfSchedule(Integer scheduleID) {
        Schedule schedule = scheduleMapper.selectScheduleByScheduleID(scheduleID);
        if (schedule != null) {
            return schedule.getUserID();
        } else {
            return null;
        }
    }

    private List<ScheduleDTO> toScheduleDTO(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            Location location = locationMapper.selectLocationByID(schedule.getLocationID());
            scheduleDTO.setLocationName(location.getName());
            scheduleDTO.setLocationAddress(location.getAddress());
            scheduleDTO.setLatitude(location.getLatitude());
            scheduleDTO.setLongitude(location.getLongitude());
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }
}
