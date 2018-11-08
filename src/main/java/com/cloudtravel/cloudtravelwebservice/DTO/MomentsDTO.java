package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MomentsDTO {

    private Integer id;
    private String username;
    private String university;
    private Date time;
    private String content;
    private List<String> imageUrls;
}
