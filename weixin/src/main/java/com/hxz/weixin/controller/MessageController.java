package com.hxz.weixin.controller;

import com.hxz.weixin.utils.MessageUtil;
import com.hxz.weixin.utils.TextMessageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 *
 * 类名称: BaseMessage
 * 类描述: 回复消息接口
 * @author xueqin
 * 创建时间:2018年3月4日
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

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
            }
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
