package com.hxz.weixin.service.impl;

import com.hxz.weixin.domain.User;
import com.hxz.weixin.repository.UserRepository;
import com.hxz.weixin.service.UserService;
import com.hxz.weixin.utils.WeiXinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{

    private static final String url = "https://api.weixin.qq.com/cgi-bin/user/info";
    private static UserRepository userRepository;

    @Override
    public User saveUser(String accessToken, String toUserName) {
        JSONObject json = WeiXinUtil.doGetstr(url + "?access_token=" + accessToken + "&openid=" + toUserName);
        System.out.println(json.toString());
        LocalDateTime time = LocalDateTime.now();
        User user = new User();
        user.setNickName(json.getString("nickname"));
        user.setSex(json.getInt("sex"));
        user.setCity(json.getString("city"));
        user.setProvince(json.getString("province"));
        user.setCountry(json.getString("country"));
        user.setHeadImgUrl(json.getString("headimgurl"));
        user.setSubcribeTime(time);
        user.setCreateTime(time);
        return userRepository.save(user);
    }

    @Override
    public String delUser(String toUserName) {
        String res = "success";
        return res;
    }
}
