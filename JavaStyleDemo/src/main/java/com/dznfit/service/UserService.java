package com.dznfit.service;

import com.dznfit.entity.User;

import java.io.IOException;

public interface UserService {
    User login(User u) throws IOException;
}
