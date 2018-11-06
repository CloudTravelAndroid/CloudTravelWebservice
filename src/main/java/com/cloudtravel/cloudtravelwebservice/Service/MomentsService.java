package com.cloudtravel.cloudtravelwebservice.Service;

import com.cloudtravel.cloudtravelwebservice.DTO.MomentsCommentDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.MomentsDTO;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsCommentForm;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsForm;

import java.util.List;

public interface MomentsService {

    void createMoments(Integer userID, MomentsForm momentsForm);

    List<MomentsDTO> findLatestMoments(Integer size);

    List<MomentsDTO> findLatestMomentsByUserID(Integer userID, Integer size);

    List<MomentsCommentDTO> findMomentsCommentByMomentsID(Integer momentsID, Integer size);

    void createMomentsComment(Integer userID, MomentsCommentForm momentsCommentForm);

    void deleteMomentsByMomentsID(Integer momentsID);

    Integer findUserIDOfMoments(Integer momentsID);
}
