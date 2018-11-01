package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Province;
import com.cloudtravel.cloudtravelwebservice.DO.University;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UniversityMapper {

    @Select("select * from province")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    List<Province> selectAllProvinces();

    @Select("select * from university where province_id = #{ID}")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "provinceID", column = "provinceID", javaType = Integer.class),
            @Result(property = "website", column = "website", javaType = String.class),
            @Result(property = "city", column = "city", javaType = String.class),
            @Result(property = "address", column = "address", javaType = String.class),
            @Result(property = "openTime", column = "open_time", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class)
    })
    List<University> selectUniversitiesByProvinceID(Integer ID);

    @Select("select * from university where id = #{ID}")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "provinceID", column = "provinceID", javaType = Integer.class),
            @Result(property = "website", column = "website", javaType = String.class),
            @Result(property = "city", column = "city", javaType = String.class),
            @Result(property = "address", column = "address", javaType = String.class),
            @Result(property = "openTime", column = "open_time", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class)
    })
    University selectUniversityByUniversityID(Integer ID);

    @Select("select * from university where name like concat('%', concat(#{name}, '%'))")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "provinceID", column = "provinceID", javaType = Integer.class),
            @Result(property = "website", column = "website", javaType = String.class),
            @Result(property = "city", column = "city", javaType = String.class),
            @Result(property = "address", column = "address", javaType = String.class),
            @Result(property = "openTime", column = "open_time", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class)
    })
    List<University> selectUniversitiesByMatchingName(String name);
}
