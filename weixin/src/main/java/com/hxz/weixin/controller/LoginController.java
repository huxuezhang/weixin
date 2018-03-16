package com.hxz.weixin.controller;

import com.hxz.weixin.service.UserService;
import com.hxz.weixin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 * @author xueqin
 * 创建时间:2018年3月4日
 */
@Controller
@RequestMapping(value = "wx")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                out.write(echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }

    }

    @RequestMapping(method= RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        String message = "";
        //处理文本类型，实现输入1，回复相应的封装的内容
        if("text".equals(msgType)){
            if("1".equals(content)){
                TextMessageUtil textMessage = new TextMessageUtil();
                message = textMessage.initMessage(fromUserName, toUserName);
                String token = WeiXinUtil.getAccess_Token(request);
                userService.saveUser(token, fromUserName);
            }
            if("2".equals(content)){
                String accessToken  = WeiXinUtil.getAccess_Token(request);
                String menu = MenuUtil.initMenu();
                System.out.println(menu);
                int result = MenuUtil.createMenu(accessToken,menu);
                if(result==0){
                    System.out.println("菜单创建成功");
                }else{
                    System.out.println("错误码"+result);
                }
            }
        } else if ("event".equalsIgnoreCase(msgType)) {
            String token = WeiXinUtil.getAccess_Token(request);
            userService.saveUser(token, toUserName);
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();
    }


}
