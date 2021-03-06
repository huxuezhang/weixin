package com.hxz.weixin.utils;

import com.hxz.weixin.security.SHA1;

import java.util.Arrays;

/**
 *
 * 类名称: CheckUtil
 * 类描述: 请求校验
 * @author yuanjun
 * 创建时间:2018年3月4日
 */
public class CheckUtil {

    private static final String token = "hxz123456";
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] str = new String[]{token,timestamp,nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ;i<str.length;i++){
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }


//    public static void main(String[] args){
//        String accessToken  = WeiXinUtil.getAccess_Token();
//        String menu = MenuUtil.initMenu();
//        System.out.println(menu);
//        int result = MenuUtil.createMenu(accessToken,menu);
//        if(result==0){
//            System.out.println("菜单创建成功");
//        }else{
//            System.out.println("错误码"+result);
//        }
//    }
}
