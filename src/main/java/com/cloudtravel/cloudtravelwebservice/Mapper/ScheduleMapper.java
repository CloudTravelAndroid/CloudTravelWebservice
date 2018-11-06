package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Schedule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ScheduleMapper {

    @Select("select * from schedule where user_id = #{userID} and date(time) = curdate() order by time")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "location_id", property = "locationID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "memo", property = "memo", javaType = String.class)
    })
    List<Schedule> selectCurrentScheduleByUserID(Integer userID);

    @Select("select * from schedule where user_id = #{userID} and date(time) = date(#{time}) order by time")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "location_id", property = "locationID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "memo", property = "memo", javaType = String.class)
    })
    List<Schedule> selectScheduleByUserIDAndTime(@Param("userID") Integer userID, @Param("time") Date time);

    @Select("select * from schedule where id = #{ID}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "location_id", property = "locationID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "memo", property = "memo", javaType = String.class)
    })
    Schedule selectScheduleByScheduleID(Integer ID);

    @Insert("insert into schedule(time, location_id, user_id, memo) values(#{time}, #{locationID}, #{userID}, #{memo})")
    int insertSchedule(Schedule schedule);

    @Delete("delete from schedule where id = #{ID}")
    int deleteScheduleByScheduleID(Integer ID);

    @Update("update schedule set time = #{time}, memo = #{memo} where id = #{ID}")
    int updateSchedule(Schedule schedule);
}
