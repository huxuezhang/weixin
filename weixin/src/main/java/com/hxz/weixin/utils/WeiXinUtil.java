package com.hxz.weixin.utils;

import com.hxz.weixin.domain.AllEnv;
import com.hxz.weixin.repository.AllEnvRepository;
import com.hxz.weixin.security.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * 类名称: WeiXinUtil
 * 类描述:
 * @author xueqin
 * 创建时间:2018年3月6日
 */
@Component
public class WeiXinUtil {

    private static AllEnvRepository allEnvRepository;

    @Autowired
    public void setAllEnvRepository(AllEnvRepository allEnvRepository) {
        WeiXinUtil.allEnvRepository = allEnvRepository;
    }

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
            + "grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 处理doget请求
     * @param url
     * @return
     */
    public static JSONObject doGetstr(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity);
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
    /**
     * 处理post请求
     * @param url
     * @return
     */
    public static JSONObject doPoststr(String url,String outStr){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            jsonObject =JSONObject.fromObject(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static AccessToken getAccessToken(){
        System.out.println("从接口中获取");
//        Jedis jedis  = RedisUtil.getJedis();
        AccessToken token = new AccessToken();
        AllEnv appIDEnv = allEnvRepository.findByUserNameAndKey(System.getenv().get("USERNAME"),"AppID");
        AllEnv appsecretEnv = allEnvRepository.findByUserNameAndKey(System.getenv().get("USERNAME"),"AppSecret");
        String url = ACCESS_TOKEN_URL.replace("APPID", appIDEnv.getValue()).replace("APPSECRET", appsecretEnv.getValue());
        JSONObject json = doGetstr(url);
        if(json!=null){
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(json.getInt("expires_in"));
//            jedis.set("access_token", json.getString("access_token"));
//            jedis.expire("access_token", 60*60*2);
        }
//        RedisUtil.returnResource(jedis);
        return token;
    }
    /**
     * 获取凭证
     * @return
     */
    public static String  getAccess_Token(HttpServletRequest request){
        System.out.println("从session取access_token");
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
//        Jedis jedis  = RedisUtil.getJedis();
//        String access_token = jedis.get("access_token");
        // TO DO
        if(StringUtils.isEmpty(access_token)){
            AccessToken token = getAccessToken();
            access_token = token.getAccess_token();
            session.setAttribute("access_token",access_token);
            session.setMaxInactiveInterval(token.getExpires_in());
        }
        return access_token;
    }

}
