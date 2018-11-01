package com.cloudtravel.cloudtravelwebservice.Service.Impl;

import com.cloudtravel.cloudtravelwebservice.DO.Province;
import com.cloudtravel.cloudtravelwebservice.DO.University;
import com.cloudtravel.cloudtravelwebservice.DTO.ProvinceDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.SimpleUniversityDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.UniversityDTO;
import com.cloudtravel.cloudtravelwebservice.Mapper.UniversityMapper;
import com.cloudtravel.cloudtravelwebservice.Service.UniversityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityMapper universityMapper;

    @Override
    public List<ProvinceDTO> getAllProvinces() {
        List<Province> provinces = universityMapper.selectAllProvinces();
        return provinces.stream().map(o -> new ProvinceDTO(o.getID(), o.getName())).collect(Collectors.toList());
    }

    @Override
    public List<SimpleUniversityDTO> findSimpleUniversityByProvinceID(Integer ID) {
        List<University> universities = universityMapper.selectUniversitiesByProvinceID(ID);
        return universities.stream().map(o -> new SimpleUniversityDTO(o.getID(), o.getName(), o.getCity()))
                .collect(Collectors.toList());
    }

    @Override
    public UniversityDTO findUniversityByUniversityID(Integer ID) {
        University university = universityMapper.selectUniversityByUniversityID(ID);
        UniversityDTO universityDTO = new UniversityDTO();
        BeanUtils.copyProperties(university, universityDTO);
        return universityDTO;
    }

    @Override
    public List<SimpleUniversityDTO> findSimpleUniversityByName(String name) {
        List<University> universities = universityMapper.selectUniversitiesByMatchingName(name);
        return universities.stream().map(o -> new SimpleUniversityDTO(o.getID(), o.getName(), o.getCity()))
                .collect(Collectors.toList());
    }
}
