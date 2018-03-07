package com.hxz.weixin.dto;

import lombok.Data;

/**
 *
 * 类名称: Button
 * 类描述: 按钮
 * @author xueqin
 * 创建时间:2018年3月6日
 */
@Data
public class Button {

    private String name;//菜单标题
    private String type;//菜单的响应动作类型
    private Button[] sub_button;

}
