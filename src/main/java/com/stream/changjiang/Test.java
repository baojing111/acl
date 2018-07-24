package com.stream.changjiang;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;

import java.util.Arrays;

/**
 * @author wb-bj355912
 * @Date:2018/6/20 15:01
 * @Description
 */
public class Test {
    private static final String CORP_SECRET = "YSySWUcQE1j-0XuWPpl8o4YIV3XuZiPOZj0IRWTbMLzb338yyO-Aid-8lgpd-8li";
    private static final String GET = "GET";
    private static final String CORP_ID = "ding4168da49920dff6c35c2f4657eb6378f";
    private static String accessToken ;


    static String getAccessToken() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setCorpid(CORP_ID);
        request.setCorpsecret(CORP_SECRET);
        request.setHttpMethod(GET);
        try {
            OapiGettokenResponse response = client.execute(request, accessToken);
            accessToken = response.getAccessToken();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    public static void main(String[] args) {
        //注册
     DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
        request.setUrl("http://newmanufacturingdaily.vaiwan.com/humanres/invoke");
        request.setAesKey("1234567890123456789012345678901234567890123");
        request.setToken("123456");
        request.setCallBackTag(Arrays.asList("attendance","bpms_instance_change"));
        OapiCallBackRegisterCallBackResponse response;
        try {
            response = client.execute(request, getAccessToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    /*   //查询
      DingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/get_call_back");
        OapiCallBackGetCallBackRequest request = new OapiCallBackGetCallBackRequest();
        request.setHttpMethod("GET");
        try {
            OapiCallBackGetCallBackResponse response = client.execute(request,getAccessToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }*/
        //删除
       /* DingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/delete_call_back");
        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
        request.setHttpMethod("GET");
        try {
            OapiCallBackDeleteCallBackResponse response = client.execute(request,getAccessToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }*/

    }


}

