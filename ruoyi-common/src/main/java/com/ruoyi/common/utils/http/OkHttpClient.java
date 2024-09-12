package com.ruoyi.common.utils.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author sett
 * @date 2023年03月25日 22:23
 * @title
 */
public class OkHttpClient {
    private volatile static okhttp3.OkHttpClient client;
    private static final Logger log = LoggerFactory.getLogger(OkHttpClient.class);
    public static String url = "https://api.ownthink.com/bot";
    public static Map<String,String> urlMap = new HashMap<String,String>(){
        {
            put("appid","420931fbec5917ee056119eefaac3cc3");
            put("userid","fgXEDXpp");
        }
    };
    public static String errorMessage = "聊天请求接口出错";



    private static okhttp3.OkHttpClient getInstance() {
        if (client == null) {
            synchronized (okhttp3.OkHttpClient.class) {
                if (client == null) {
                    client = new okhttp3.OkHttpClient.Builder()
                            .build();
                }
            }
        }
        return client;
    }
    public static String syncGet(String message) {
        Request request;
        HttpUrl.Builder urlBuilder =HttpUrl.parse(url)
                .newBuilder();
        for(String key:urlMap.keySet()){
            urlBuilder.addQueryParameter(key,urlMap.get(key));
        }
        urlBuilder.addQueryParameter("spoken",message);
        final Request.Builder builder = new Request.Builder().url(urlBuilder.build().url());
        log.info(urlBuilder.build().url().toString());
        try {

            request = builder.build();
            Response response = OkHttpClient.getInstance().newCall(request).execute();
            String result = response.body().string();
//            log.info(result);
            if (!response.isSuccessful()) {
                throw new IOException("三方接口返回http状态码为" + response.code());
            }
            return result;
        } catch (Exception e) {
            log.error("remote interface url:{} have a ecxeption {}", url, e);
            throw new RuntimeException("三方接口返回异常");
        }
    }


    public static String sendMessage(String message){
        JSONObject object = JSONObject.parseObject(syncGet(message));
        if("success".equals(object.get("message"))){
            String o = (String)object.getJSONObject("data").getJSONObject("info").get("text");
            return o;
        }else{
            log.info(errorMessage);
            return errorMessage;
        }
    }


}
