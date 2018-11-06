package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class MomentsCommentDTO {

    private Integer ID;
    private String username;
    private Date time;
    private String content;

    public MomentsCommentDTO(Integer ID, String username, Date time, String content) {
        this.ID = ID;
        this.username = username;
        this.time = time;
        this.content = content;
    }
}
