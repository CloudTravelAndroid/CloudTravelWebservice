package com.cloudtravel.cloudtravelwebservice.Controller;

import com.cloudtravel.cloudtravelwebservice.DTO.BaseResponse;
import com.cloudtravel.cloudtravelwebservice.DTO.ScheduleDTO;
import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;
import com.cloudtravel.cloudtravelwebservice.Exception.UnauthorizedException;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleForm;
import com.cloudtravel.cloudtravelwebservice.Form.ScheduleUpdateForm;
import com.cloudtravel.cloudtravelwebservice.Service.ScheduleService;
import com.cloudtravel.cloudtravelwebservice.Util.DateUtil;
import com.cloudtravel.cloudtravelwebservice.Util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/schedules/current")
    public BaseResponse<List<ScheduleDTO>> getCurrentSchedule(HttpServletRequest request) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        List<ScheduleDTO> scheduleDTOS = scheduleService.findCurrentScheduleByUserID(userID);
        BaseResponse<List<ScheduleDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(scheduleDTOS);
        return response;
    }

    @PostMapping("/schedules/time")
    public BaseResponse<List<ScheduleDTO>> getSchedule(HttpServletRequest request, String time) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        Date date = DateUtil.str2Date(time, DateUtil.FORMAT_yMdHMS);
        List<ScheduleDTO> scheduleDTOS = scheduleService.findScheduleByUserIDAndTime(userID, date);
        BaseResponse<List<ScheduleDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(scheduleDTOS);
        return response;
    }

    @PostMapping("/schedules")
    public BaseResponse createSchedule(HttpServletRequest request, @RequestBody ScheduleForm scheduleForm) {
        // Todo: Verify the scheduleForm
        //log.info(scheduleForm.toString());
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        scheduleService.createSchedule(userID, scheduleForm);
        return new BaseResponse(ErrorCode.SUCCESS);
    }

    @PostMapping("/schedules/delete")
    public BaseResponse deleteSchedule(HttpServletRequest request, Integer scheduleID) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        if (!userID.equals(scheduleService.findUserIDOfSchedule(scheduleID))) {
            throw new UnauthorizedException();
        }
        scheduleService.deleteScheduleByScheduleID(scheduleID);
        return new BaseResponse(ErrorCode.SUCCESS);
    }

    @PostMapping("/schedules/update")
    public BaseResponse updateSchedule(HttpServletRequest request, @RequestBody ScheduleUpdateForm updateForm) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        if (!userID.equals(scheduleService.findUserIDOfSchedule(updateForm.getId()))) {
            throw new UnauthorizedException();
        }
        scheduleService.updateSchedule(updateForm);
        return new BaseResponse(ErrorCode.SUCCESS);
    }
}
