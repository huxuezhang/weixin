package com.hxz.weixin.utils;

import com.hxz.weixin.dto.MessageText;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * 类名称: MessageTest
 * 类描述: 消息内容实体
 * @author xueqin
 * 创建时间:20178年3月4日
 */
public class TextMessageUtil{
    /**
     * 将发送消息封装成对应的xml格式
     */
    public  String messageToxml(MessageText message) {
        XStream xstream  = new XStream();
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     */
    public  String initMessage(String FromUserName, String ToUserName) {
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("欢迎关注机械振动工程党支部");
        text.setCreateTime(System.currentTimeMillis());
        text.setMsgType("text");
        return  messageToxml(text);
    }
}
