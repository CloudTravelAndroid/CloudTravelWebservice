package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Location;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface LocationMapper {

    @Select("select * from location where id = #{ID}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "uid", property = "UID", javaType = String.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "address", property = "address", javaType = String.class),
            @Result(column = "latitude", property = "latitude", javaType = Double.class),
            @Result(column = "longitude", property = "longitude", javaType = Double.class)
    })
    Location selectLocationByID(Integer ID);

    @Insert("insert into location(uid, name, address, latitude, longitude) values(#{UID}, #{name}, " +
            "#{address}, #{latitude}, #{longitude})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "ID")
    void insertLocation(Location location);

    @Select("select * from location where uid = #{UID}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "uid", property = "UID", javaType = String.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "address", property = "address", javaType = String.class),
            @Result(column = "latitude", property = "latitude", javaType = Double.class),
            @Result(column = "longitude", property = "longitude", javaType = Double.class)
    })
    Location selectLocationByUID(String UID);
}
