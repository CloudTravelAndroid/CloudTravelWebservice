package com.cloudtravel.cloudtravelwebservice.DTO;

import lombok.Data;

@Data
public class ProvinceDTO {

    private Integer ID;
    private String name;

    public ProvinceDTO(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
