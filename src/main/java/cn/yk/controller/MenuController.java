package cn.yk.controller;

import cn.yk.utils.AccessTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by yuanyukai on 2018-4-27.
 */
@Controller
public class MenuController {
    @RequestMapping("/setMenu")
    public void setMenuu(){
    String user_define_menu = "{\"button\":[{\"type\":\"click\",\"name\":\"项目管理\",\"key\":\"20_PROMANAGE\"},{\"type\":\"click\",\"name\":\"机构运作\",\"key\":\"30_ORGANIZATION\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
    //此处改为自己想要的结构体，替换即可
    String access_token= AccessTokenUtil.getAccessToken();

    String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
try {
        URL url = new URL(action);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒

        http.connect();
        OutputStream os= http.getOutputStream();
        os.write(user_define_menu.getBytes("UTF-8"));//传入参数
        os.flush();
        os.close();

        InputStream is =http.getInputStream();
        int size =is.available();
        byte[] jsonBytes =new byte[size];
        is.read(jsonBytes);
        String message=new String(jsonBytes,"UTF-8");
        System.out.println(message);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
