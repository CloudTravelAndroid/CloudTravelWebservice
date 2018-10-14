package com.cloudtravel.cloudtravelwebservice.Service;

import com.cloudtravel.cloudtravelwebservice.DTO.ProvinceDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.SimpleUniversityDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.UniversityDTO;

import java.util.List;

public interface UniversityService {

    List<ProvinceDTO> getAllProvinces();

    List<SimpleUniversityDTO> findSimpleUniversitiesByProvinceID(Integer ID);

    UniversityDTO findUniversityByUniversityID(Integer ID);
}
