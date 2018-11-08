package com.cloudtravel.cloudtravelwebservice.Controller;

import com.cloudtravel.cloudtravelwebservice.DTO.BaseResponse;
import com.cloudtravel.cloudtravelwebservice.DTO.MomentsCommentDTO;
import com.cloudtravel.cloudtravelwebservice.DTO.MomentsDTO;
import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;
import com.cloudtravel.cloudtravelwebservice.Exception.UnauthorizedException;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsCommentForm;
import com.cloudtravel.cloudtravelwebservice.Form.MomentsForm;
import com.cloudtravel.cloudtravelwebservice.Service.MomentsService;
import com.cloudtravel.cloudtravelwebservice.Util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class MomentsController {

    @Autowired
    private MomentsService momentsService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/moments/latest")
    public BaseResponse<List<MomentsDTO>> getLatestMoments(Integer size) {
        List<MomentsDTO> momentsDTOS = momentsService.findLatestMoments(size);
        BaseResponse<List<MomentsDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(momentsDTOS);
        return response;
    }

    @PostMapping("/moments/me")
    public BaseResponse<List<MomentsDTO>> getLatestUserRelatedMoments(HttpServletRequest request, Integer size) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        List<MomentsDTO> momentsDTOS = momentsService.findLatestMomentsByUserID(userID, size);
        BaseResponse<List<MomentsDTO>> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(momentsDTOS);
        return response;
    }

//    @PostMapping("/moments/body")
//    public BaseResponse createMoments(HttpServletRequest request, @RequestBody MomentsForm momentsForm) {
//        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
//        momentsService.createMoments(userID, momentsForm);
//        return new BaseResponse(ErrorCode.SUCCESS);
//    }

    @PostMapping("/moments")
    public BaseResponse createMoments(HttpServletRequest request) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        MomentsForm momentsForm = new MomentsForm();
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = params.getFiles("images");
        momentsForm.setContent(params.getParameter("content"));
        momentsForm.setImages(files);
        Integer universityID = null;
        if (params.getParameter("universityID") != null) {
            universityID = Integer.valueOf(params.getParameter("universityID"));
        }
        momentsForm.setUniversityID(universityID);
        log.info(momentsForm.toString());
        momentsService.createMoments(userID, momentsForm);
        return new BaseResponse(ErrorCode.SUCCESS);
    }

    @PostMapping("/moments/comments")
    public BaseResponse<List<MomentsCommentDTO>> getMomentsCommentsByMomentsID(Integer momentsID, Integer size) {
        List<MomentsCommentDTO> momentsCommentDTOS = momentsService.findMomentsCommentByMomentsID(momentsID, size);
        BaseResponse<List<MomentsCommentDTO>> response = new BaseResponse<>();
        response.setObject(momentsCommentDTOS);
        return response;
    }

    @PostMapping("/moments/actions/delete")
    public BaseResponse deleteMoments(HttpServletRequest request, Integer momentsID) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        if (!userID.equals(momentsService.findUserIDOfMoments(momentsID))) {
            throw new UnauthorizedException();
        }
        momentsService.deleteMomentsByMomentsID(momentsID);
        return new BaseResponse(ErrorCode.SUCCESS);
    }

    @PostMapping("/moments/comments/actions/create")
    public BaseResponse createMomentsComment(HttpServletRequest request, @RequestBody MomentsCommentForm momentsCommentForm) {
        Integer userID = RedisUtil.getUserIdFromRequestHeader(redisTemplate, request);
        momentsService.createMomentsComment(userID, momentsCommentForm);
        return new BaseResponse(ErrorCode.SUCCESS);
    }
}
