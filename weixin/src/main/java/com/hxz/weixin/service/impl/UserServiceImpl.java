package com.hxz.weixin.service.impl;

import com.hxz.weixin.service.UserService;
import com.hxz.weixin.utils.WeiXinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{
    private static final String url = "https://api.weixin.qq.com/cgi-bin/user/info";

    @Override
    public String saveUser(String accessToken, String toUserName) {

        String res = "success";
        JSONObject json = WeiXinUtil.doGetstr(url + "?access_token=" + accessToken + "&openid=" + toUserName);
        System.out.println(json.toString());
        String nickName = json.getString("nickname");
        int sex = json.getInt("sex");
        String city = json.getString("city");
        String province = json.getString("province");
        String country = json.getString("country");
        String headimgurl = json.getString("headimgurl");
        Date subcribeTime = new Date();


        return res;
    }

    @Override
    public String delUser(String toUserName) {
        String res = "success";
        return res;
    }
}
