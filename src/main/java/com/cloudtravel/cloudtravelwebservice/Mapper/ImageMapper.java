package com.cloudtravel.cloudtravelwebservice.Mapper;

import com.cloudtravel.cloudtravelwebservice.DO.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Component
public interface ImageMapper {

    @Insert("insert into image(url) values(#{URL})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "ID")
    void insertImage(Image image);
}
