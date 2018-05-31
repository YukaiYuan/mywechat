package cn.yk.utils;

import cn.yk.constants.MyWechartConstant;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by yuanyukai on 2018-4-27.
 */
public class AccessTokenUtil {
    public static String getAccessToken(){
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
                MyWechartConstant.APP_ID+"&secret="+MyWechartConstant.APP_SECRET;

        String result=HttpClientUtil.doGet(url);
        JSONObject jsonObj= JSONObject.parseObject(result);
        String access_token= jsonObj.getString("access_token");
        System.out.print(access_token);
        return access_token;
    }
}
