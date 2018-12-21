package com.dznfit.service;

import com.dznfit.dao.UserMapper;
import com.dznfit.entity.User;
import com.dznfit.dao.UserMapper;
import com.dznfit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        User users = userMapper.selectByUser(user);
        if (users != null) {
            return users;
        }
        return null;
    }
}
