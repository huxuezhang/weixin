package com.hxz.weixin.dto;

import lombok.Data;

/**
 *
 * 类名称: MessageTest
 * 类描述: 消息内容实体
 * @author xueqin
 * 创建时间:20178年3月4日
 */
@Data
public class MessageText extends BaseMessage {

    private String Content;//文本消息内容

    private String MsgId;//消息id，64位整型

    public MessageText(){

    }
    
    public MessageText(String toUserName, String fromUserName,
                       long createTime, String msgType, String content, String msgId) {
        super();
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Content = content;
        MsgId = msgId;
    }


}
