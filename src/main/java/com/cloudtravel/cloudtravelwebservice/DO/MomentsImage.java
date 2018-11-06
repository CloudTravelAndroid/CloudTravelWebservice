package com.cloudtravel.cloudtravelwebservice.DO;

import lombok.Data;

@Data
public class MomentsImage {

    private Integer momentsID;
    private Integer imageID;

    public MomentsImage(Integer momentsID, Integer imageID) {
        this.momentsID = momentsID;
        this.imageID = imageID;
    }
}
