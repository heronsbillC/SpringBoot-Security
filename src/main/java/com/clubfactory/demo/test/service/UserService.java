package com.clubfactory.demo.test.service;

import com.clubfactory.demo.test.pojo.Users;

public interface UserService {
    public Users login(String userName, String password);
}