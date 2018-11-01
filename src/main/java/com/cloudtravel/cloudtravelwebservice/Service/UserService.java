package com.cloudtravel.cloudtravelwebservice.Service;


import com.cloudtravel.cloudtravelwebservice.DO.User;
import com.cloudtravel.cloudtravelwebservice.DTO.UserDTO;

public interface UserService {

    UserDTO getUserInfoByUserID(Integer ID);

    UserDTO getUserInfoByUsername(String username);

    Integer createUser(User user);

    int deleteUserByUserID(Integer ID);

    User findUserByUsername(String username);
}
