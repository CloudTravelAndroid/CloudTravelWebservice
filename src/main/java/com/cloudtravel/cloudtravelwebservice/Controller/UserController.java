package com.cloudtravel.cloudtravelwebservice.Controller;

import com.cloudtravel.cloudtravelwebservice.Constant.RedisConstant;
import com.cloudtravel.cloudtravelwebservice.DO.User;
import com.cloudtravel.cloudtravelwebservice.DTO.BaseResponse;
import com.cloudtravel.cloudtravelwebservice.DTO.UserDTO;
import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;
import com.cloudtravel.cloudtravelwebservice.Exception.SignInException;
import com.cloudtravel.cloudtravelwebservice.Exception.SignUpException;
import com.cloudtravel.cloudtravelwebservice.Exception.TokenExpiredException;
import com.cloudtravel.cloudtravelwebservice.Form.UserSignInForm;
import com.cloudtravel.cloudtravelwebservice.Form.UserSignUpForm;
import com.cloudtravel.cloudtravelwebservice.Service.UserService;
import com.cloudtravel.cloudtravelwebservice.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/actions/signUp")
    public BaseResponse<String> signUp(@RequestBody UserSignUpForm userSignUpForm) {
        // Todo: Verify the userSignUpForm
        Integer userID = userService.createUser(userSignUpForm);
        if (userID == null) {
            throw new SignUpException(ErrorCode.USERNAME_ALREADY_EXIST);
        }
        String token = UUID.randomUUID().toString();
        Integer expireTime = RedisConstant.EXPIRE_TIME;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_FORMAT, token), userID.toString(), expireTime, TimeUnit.SECONDS);
        BaseResponse<String> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(token);
        return response;
    }

    @PostMapping("/actions/signIn")
    public BaseResponse<String> signIn(@RequestBody UserSignInForm userSignInForm) {
        User user = userService.findUserByUsername(userSignInForm.getName());
        if (user == null) {
            throw new SignInException(ErrorCode.USERNAME_NOT_EXIST);
        }
        if (!user.getPassword().equals(userSignInForm.getPassword())) {
            throw new SignInException(ErrorCode.INCORRECT_PASSWORD);
        }
        Integer userId = user.getID();
        String token = UUID.randomUUID().toString();
        Integer expireTime = RedisConstant.EXPIRE_TIME;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_FORMAT, token), userId.toString(), expireTime, TimeUnit.SECONDS);
        BaseResponse<String> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(token);
        return response;
    }

    @GetMapping("/actions/logOut")
    public BaseResponse logOut(HttpServletRequest request) {
        RedisUtil.deleteTokenFromRequestHeader(redisTemplate, request);
        return new BaseResponse(ErrorCode.SUCCESS);
    }

    @GetMapping("/info")
    public BaseResponse<UserDTO> getUserInfo(HttpServletRequest httpServletRequest) {
        Integer userId = RedisUtil.getUserIdFromRequestHeader(redisTemplate, httpServletRequest);
        if (userId == null) {
            throw new TokenExpiredException();
        }
        UserDTO userDTO = userService.getUserInfoByUserID(userId);
        BaseResponse<UserDTO> response = new BaseResponse<>(ErrorCode.SUCCESS);
        response.setObject(userDTO);
        return response;
    }
}
