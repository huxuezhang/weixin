package com.hxz.weixin.service;

public interface UserService {
    String saveUser(String accessToken, String toUserName);
    String delUser(String toUserName);
}
