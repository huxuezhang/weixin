package com.hxz.weixin.service;

import com.hxz.weixin.domain.User;

public interface UserService {
    User saveUser(String accessToken, String toUserName);
    String delUser(String toUserName);
}
