package com.worthy.ly.first;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ly on 2018/1/25.
 */
public class WechatSign {

    /*@Test
    public Object loadJSPSign(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        Map respMap = new LinkedHashMap();//阿里表单值传需要的参数
        try {
            respMap=super.checkParameter(request, new String[]{"teachId"});
            if(Integer.parseInt(respMap.get("code").toString())!=HttpServletResponse.SC_OK){
                return respMap;
            }
            String teachId=request.getParameter("teachId");
            User user=(User)baseManager.getObject(User.class.getName(),teachId);
            String branchName=user.getTeachArea().getBranch().getName();
            String appID=WeiXinUtil.getValue(branchName+".AppID")==null?WeiXinUtil.getValue("chengGong.AppID"):WeiXinUtil.getValue(branchName+".AppID");
            String appSecret=WeiXinUtil.getValue(branchName+".AppSecret")==null?WeiXinUtil.getValue("chengGong.AppSecret"):WeiXinUtil.getValue(branchName+".AppSecret");
            String ticket = SignUtil.getTicket(appID, appSecret);
            String  url= request.getHeader("referer");
            if(StringUtils.isEmpty(url)){
                respMap.put("code",HttpServletResponse.SC_UNAUTHORIZED);
                respMap.put("message","来源地址错误.");
            } else {
                Map map=SignUtil.getSign(ticket,url.toString());
                map.put("appId",appID);
                respMap.put("data",map);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            respMap.put("code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            respMap.put("message","服务器错误.");
        } finally {
            super.doCors(request, response);
        }
        return respMap;
    }*/


}
