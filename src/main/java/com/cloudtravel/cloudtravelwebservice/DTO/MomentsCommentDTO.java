package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class MomentsCommentDTO {

    private Integer id;
    private String username;
    private Date time;
    private String content;

    public MomentsCommentDTO(Integer id, String username, Date time, String content) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.content = content;
    }
}
