package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("select * from user where id = #{ID}")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "phoneNumber", column = "phone_number", javaType = String.class),
            @Result(property = "universityID", column = "university_id", javaType = Integer.class)
    })
    User selectUserByUserID(Integer ID);

    @Select("select * from user where name = #{username} ")
    @Results({
            @Result(property = "ID", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "phoneNumber", column = "phone_number", javaType = String.class),
            @Result(property = "universityID", column = "university_id", javaType = Integer.class)
    })
    User selectUserByUsername(String username);

    @Insert("insert into user(name, password, email, phone_number, university_id) values(#{name}, #{password}, " +
            "#{email}, #{phoneNumber}, #{universityID})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "ID")
    int insertUser(User user);

    @Delete("delete from user where id = #{ID}")
    int deleteUserByUserID(Integer ID);

    @Select("select name from user where id = #{ID}")
    @ResultType(String.class)
    String selectUsernameByUserID(Integer ID);
}
