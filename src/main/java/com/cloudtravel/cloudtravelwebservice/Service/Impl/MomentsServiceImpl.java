package com.cloudtravel.cloudtravelwebservice.Service.Impl;

import com.cloudtravel.cloudtravelwebservice.DO.Image;
import com.cloudtravel.cloudtravelwebservice.DO.Moments;
import com.cloudtravel.cloudtravelwebservice.DO.MomentsComment;
import com.cloudtravel.cloudtravelwebservice.DO.MomentsImage;
import com.cloudtravel.cloudtravelwebservice.DTO.MomentsCommentDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.MomentsDTO;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsCommentForm;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsForm;
import com.cloudtravel.cloudtravelwebservice.Mapper.ImageMapper;
import com.cloudtravel.cloudtravelwebservice.Mapper.MomentsMapper;
import com.cloudtravel.cloudtravelwebservice.Mapper.UniversityMapper;
import com.cloudtravel.cloudtravelwebservice.Mapper.UserMapper;
import com.cloudtravel.cloudtravelwebservice.Service.MomentsService;
import com.cloudtravel.cloudtravelwebservice.Util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MomentsServiceImpl implements MomentsService {

    @Autowired
    private MomentsMapper momentsMapper;

    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void createMoments(Integer userID, MomentsForm momentsForm) {
        Moments moments = new Moments();
        moments.setUserID(userID);
        moments.setUniversityID(momentsForm.getUniversityID());
        moments.setContent(momentsForm.getContent());
        momentsMapper.insertMoments(moments);
        for (MultipartFile file : momentsForm.getImages()) {
            String URL = FileUtil.imageUpload(file);
            Image image = new Image();
            image.setURL(URL);
            imageMapper.insertImage(image);
            MomentsImage momentsImage = new MomentsImage(moments.getID(), image.getID());
            momentsMapper.insertMomentsImage(momentsImage);
        }
    }

    @Override
    public List<MomentsDTO> findLatestMoments(Integer size) {
        List<Moments> moments = momentsMapper.selectLatestMoments(size);
        return toMomentsDTO(moments);
    }

    @Override
    public List<MomentsDTO> findLatestMomentsByUserID(Integer userID, Integer size) {
        List<Moments> moments = momentsMapper.selectLatestMomentsByUserID(userID, size);
        return toMomentsDTO(moments);
    }

    @Override
    public List<MomentsCommentDTO> findMomentsCommentByMomentsID(Integer momentsID, Integer size) {
        List<MomentsComment> momentsComments = momentsMapper.selectMomentsCommentByMomentsID(momentsID, size);
        return momentsComments.stream().map(o -> new MomentsCommentDTO(o.getID(),
                userMapper.selectUsernameByUserID(o.getUserID()), o.getTime(), o.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public void createMomentsComment(Integer userID, MomentsCommentForm momentsCommentForm) {
        MomentsComment momentsComment = new MomentsComment();
        BeanUtils.copyProperties(momentsCommentForm, momentsComment);
        momentsComment.setUserID(userID);
        momentsMapper.insertMomentsComment(momentsComment);
    }

    @Override
    public void deleteMomentsByMomentsID(Integer momentsID) {
        momentsMapper.deleteMoments(momentsID);
    }

    private List<MomentsDTO> toMomentsDTO(List<Moments> momentsList) {
        List<MomentsDTO> momentsDTOS = new ArrayList<>();
        for (Moments moments : momentsList) {
            MomentsDTO momentsDTO = new MomentsDTO();
            BeanUtils.copyProperties(moments, momentsDTO);
            momentsDTO.setUsername(userMapper.selectUserByUserID(moments.getUserID()).getName());
            momentsDTO.setUniversity(universityMapper.selectUniversityByUniversityID(moments.getUniversityID())
                    .getName());
            momentsDTO.setImageUrls(momentsMapper.selectImageURLByMomentsID(moments.getID()));
            momentsDTOS.add(momentsDTO);
        }
        return momentsDTOS;
    }

    @Override
    public Integer findUserIDOfMoments(Integer momentsID) {
        return momentsMapper.selectUserIDByMomentsID(momentsID);
    }
}
