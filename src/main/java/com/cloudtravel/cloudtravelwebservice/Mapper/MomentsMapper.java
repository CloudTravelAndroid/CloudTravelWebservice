package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Moments;
import com.cloudtravel.cloudtravelwebservice.DO.MomentsComment;
import com.cloudtravel.cloudtravelwebservice.DO.MomentsImage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface MomentsMapper {

    @Insert("insert into moments(user_id, university_id, content) values(#{userID}, #{universityID}, #{content})")
    void insertMoments(Moments moments);

    @Select("select user_id from moments where id = #{momentsID}")
    Integer selectUserIDByMomentsID(Integer momentsID);


    @Select("select * from moments order by time desc limit #{size}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "university_id", property = "universityID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "content", property = "content", javaType = String.class)
    })
    List<Moments> selectLatestMoments(Integer size);

    @Select("select * from moments where user_id = #{userID} order by time desc limit #{size}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "university_id", property = "universityID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "content", property = "content", javaType = String.class)
    })
    List<Moments> selectLatestMomentsByUserID(@Param("userID") Integer userID, @Param("size") Integer size);

    @Delete("delete from moments where id = #{momentsID}")
    void deleteMoments(Integer momentsID);

    @Select("select * from moments_comment where moments_id = #{momentsID} order by time limit #{size}")
    @Results({
            @Result(column = "id", property = "ID", javaType = Integer.class),
            @Result(column = "moments_id", property = "momentsID", javaType = Integer.class),
            @Result(column = "user_id", property = "userID", javaType = Integer.class),
            @Result(column = "time", property = "time", javaType = Date.class),
            @Result(column = "content", property = "content", javaType = String.class)
    })
    List<MomentsComment> selectMomentsCommentByMomentsID(@Param("momentsID") Integer momentsID, @Param("size") Integer size);

    @Insert("insert into moments_comment(moments_id, user_id, content) values(#{momentsID}, #{userID}, #{content})")
    void insertMomentsComment(MomentsComment momentsComment);

    @Insert("insert into moments_image(moments_id, image_id) values(#{momentsID}, #{imageID})")
    void insertMomentsImage(MomentsImage momentsImage);

    @Select("select url from moments_image, image where image_id = id and moments_id = #{ID}")
    List<String> selectImageURLByMomentsID(Integer ID);
}
