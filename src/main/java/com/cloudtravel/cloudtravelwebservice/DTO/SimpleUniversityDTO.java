package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

@Data
public class SimpleUniversityDTO {

    private Integer ID;
    private String name;
    private String city;

    public SimpleUniversityDTO(Integer ID, String name, String city) {
        this.ID = ID;
        this.name = name;
        this.city = city;
    }
}
