package com.cloudtravel.cloudtravelwebservice.Controller;

import com.cloudtravel.cloudtravelwebservice.DTO.BaseResponse;
import com.cloudtravel.cloudtravelwebservice.DTO.ProvinceDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.SimpleUniversityDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.UniversityDTO;
import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;
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
    public BaseResponse<List<ProvinceDTO>> getAllProvinces() {
        List<ProvinceDTO> provinceDTOS = universityService.getAllProvinces();
        BaseResponse<List<ProvinceDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(provinceDTOS);
        return response;
    }

    @PostMapping("/provinces/{ID}")
    public BaseResponse<List<SimpleUniversityDTO>> findSimpleUniversityByProvinceID(@PathVariable("ID") Integer ID) {
        List<SimpleUniversityDTO> simpleUniversityDTOS = universityService.findSimpleUniversityByProvinceID(ID);
        BaseResponse<List<SimpleUniversityDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(simpleUniversityDTOS);
        return response;
    }

    @PostMapping("/universities/{ID}")
    public BaseResponse<UniversityDTO> findUniversityByUniversityID(@PathVariable("ID") Integer ID) {
        UniversityDTO universityDTO = universityService.findUniversityByUniversityID(ID);
        BaseResponse<UniversityDTO> response = new BaseResponse<>();
        response.setObject(universityDTO);
        return response;
    }

    @PostMapping("/universities/search")
    public BaseResponse<List<SimpleUniversityDTO>> findSimpleUniversityByName(String name) {
        List<SimpleUniversityDTO> simpleUniversityDTOS = universityService.findSimpleUniversityByName(name);
        BaseResponse<List<SimpleUniversityDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(simpleUniversityDTOS);
        return response;
    }
}
