package com.cloudtravel.cloudtravelwebservice.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MomentsForm {

    private Integer universityID;
    private String content;
    private List<MultipartFile> images;
}
