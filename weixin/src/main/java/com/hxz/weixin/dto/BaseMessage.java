package com.hxz.weixin.dto;

import lombok.Data;

/**
 *
 * 类名称: BaseMessage
 * 类描述: 回复消息的基类
 * @author xueqin
 * 创建时间:2018年3月4日
 */
@Data
public class BaseMessage {

    protected String ToUserName;
    protected String FromUserName;
    protected long CreateTime;
    protected String MsgType;

}
