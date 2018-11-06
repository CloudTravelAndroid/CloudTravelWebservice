package com.cloudtravel.cloudtravelwebservice.Service.Impl;

import com.cloudtravel.cloudtravelwebservice.DO.User;
import com.cloudtravel.cloudtravelwebservice.DTO.UserDTO;
import com.cloudtravel.cloudtravelwebservice.Form.UserSignUpForm;
import com.cloudtravel.cloudtravelwebservice.Mapper.UserMapper;
import com.cloudtravel.cloudtravelwebservice.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserInfoByUserID(Integer ID) {
        User user = userMapper.selectUserByUserID(ID);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserInfoByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public Integer createUser(UserSignUpForm userSignUpForm) {
        User user = new User();
        BeanUtils.copyProperties(userSignUpForm, user);
        userMapper.insertUser(user);
        return user.getID();
    }

    @Override
    public void deleteUserByUserID(Integer ID) {
        userMapper.deleteUserByUserID(ID);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public String findUsernameByUserID(Integer ID) {
        return userMapper.selectUsernameByUserID(ID);
    }
}
