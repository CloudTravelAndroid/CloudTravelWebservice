package com.cloudtravel.cloudtravelwebservice.Controller;

import com.cloudtravel.cloudtravelwebservice.DTO.ProvinceDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.SimpleUniversityDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.UniversityDTO;
import com.cloudtravel.cloudtravelwebservice.Service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/provinces")
    public List<ProvinceDTO> getAllProvinces() {
        return universityService.getAllProvinces();
    }

    @PostMapping("/province/{ID}")
    public List<SimpleUniversityDTO> findSimpleUniversityByProvinceID(@PathVariable("ID") Integer ID) {
        return universityService.findSimpleUniversitiesByProvinceID(ID);
    }

    @PostMapping("/university/{ID}")
    public UniversityDTO findUniversityByUniversityID(@PathVariable("ID") Integer ID) {
        return universityService.findUniversityByUniversityID(ID);
    }

}
