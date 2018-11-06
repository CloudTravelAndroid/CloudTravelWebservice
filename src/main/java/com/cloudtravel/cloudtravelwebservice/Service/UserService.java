package com.cloudtravel.cloudtravelwebservice.Service;


import com.cloudtravel.cloudtravelwebservice.DO.User;
import com.cloudtravel.cloudtravelwebservice.DTO.UserDTO;
import com.cloudtravel.cloudtravelwebservice.Form.UserSignUpForm;

public interface UserService {

    UserDTO getUserInfoByUserID(Integer ID);

    UserDTO getUserInfoByUsername(String username);

    Integer createUser(UserSignUpForm userSignUpForm);

    void deleteUserByUserID(Integer ID);

    User findUserByUsername(String username);

    String findUsernameByUserID(Integer ID);
}
