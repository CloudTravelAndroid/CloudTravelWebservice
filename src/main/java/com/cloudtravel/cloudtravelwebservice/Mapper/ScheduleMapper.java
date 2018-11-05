package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Schedule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface ScheduleMapper {

    @Select("select * from user where id = #{ID}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "date", column = "time", javaType = Date.class),
            @Result(property = "locationID", column = "location_id", javaType = Integer.class),
            @Result(property = "userID", column = "user_id", javaType = Integer.class),
            @Result(property = "memo", column = "memo", javaType = String.class)
    })
    Schedule selectBySchedueID(Integer scheduleId);

    @Select("select * from user where " +
            "where date_format(date, '%Y-%M-%D') = date_format(#{date}, '%Y-%M-%D') " +
            "and user_id = #{ID}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "date", column = "time", javaType = Date.class),
            @Result(property = "locationID", column = "location_id", javaType = Integer.class),
            @Result(property = "userID", column = "user_id", javaType = Integer.class),
            @Result(property = "memo", column = "memo", javaType = String.class)
    })
    Schedule selectByUserIDAndDate(Integer userId, Date date);

    @Insert("insert into schedule (date, location_id, user_id, memo) " +
            "values(#{date}, #{locationID}, #{userID}, #{memo})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertSchedule();

    @Delete("delete from schedule where id = #{ID}")
    void deleteByScheduleID();

    @Update("update schedule set locationId = #{locationID} where id = #{ID}")
    void updateLocationById(Integer ID, Integer locationID);

    @Update("update schedule set memo = #{memo} where id = #{ID}")
    void updateMemoByID(Integer ID, String memo);
}
