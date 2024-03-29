package com.xingchen.sdkxingchen.clinet;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.xingchen.sdkxingchen.model.LoveWords;
import com.xingchen.sdkxingchen.model.User;
import com.xingchen.sdkxingchen.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xingchen
 * @version V1.0
 * @Package com.xingchen.sdkxingchen.clinet
 * @date 2022/11/12 16:10
 */
public class ClientXingchen {

    private static final String GATEWAY_HOST = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public ClientXingchen(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(User user){
        String json = JSONUtil.toJsonStr(user);
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse httpResponse= HttpRequest.get(GATEWAY_HOST+"/api/get")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }
    public String getNameByPost(User user){
        //该方法加了@RequestParam接收JSON
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse= HttpRequest.post(GATEWAY_HOST+"/api/post")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }
    //模拟接口
    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

    //随机情话接口
    public String getLoveWordsGet(LoveWords loveWords) {
        String json = JSONUtil.toJsonStr(loveWords);
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/lovewords")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }
    //随机返回抖音美女视频
    public String getdyGirlGet() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/dygirl")
                .addHeaders(getHeaderMap(""))
                .body("")
                .execute();
        String result = httpResponse.body();
        return result;
    }

    //随机返回爬虫美女视频
    public String getpcGirlGet() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/pcgirl")
                .addHeaders(getHeaderMap(""))
                .body("")
                .execute();
        String result = httpResponse.body();
        return result;
    }

    //添加请求头，发送给网关校验用户的信息
    private Map<String, String> getHeaderMap(String body) {
        Map<String,String> map = new HashMap<>();
        map.put("accessKey",accessKey);
        //map.put("secretKey",secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body",body);
        map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        map.put("sign",SignUtils.genSign(body,secretKey));
        return map;
    }


}
