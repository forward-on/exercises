package com.worthy.ly.utils;

import com.alibaba.fastjson.JSONObject;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import com.worthy.ly.conf.SchoolnoticeConf;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by darren on 2017/4/18.
 */
@Component
public class SensorsDataUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(SensorsDataUtil.class);

    private ThreadPoolExecutor executor;

    private volatile SensorsAnalytics sa;

    @Autowired
    SchoolnoticeConf schoolnoticeConf;

    /**
     * 初始化线程池
     */
    public SensorsDataUtil() {
        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("SensorsDataUtil_thread-%d").build();
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(10000);
        executor = new ThreadPoolExecutor(10, 20, 1000 * 60, TimeUnit.MICROSECONDS, queue, threadFactory);
        LOGGER.info("SensorsDataUtil 线程池启动...");
    }

    /**
     * 单例获取神策对象
     *
     * @return
     */
    private SensorsAnalytics getSa() {
        if (sa == null) {
            synchronized (this) {
                if (sa == null) {
                    LOGGER.info("神策地址：{}", schoolnoticeConf.getSaServerUrl());
                    //初始化神策
                    sa = new SensorsAnalytics(new SensorsAnalytics.BatchConsumer(schoolnoticeConf.getSaServerUrl(), 1));
                }
            }
        }
        return sa;
    }


    /**
     * 添加神策追踪事件
     *
     * @param distinctId 追踪的id
     * @param eventName  事件名称
     * @param properties 收集属性
     */
    private void addSaTrack(String distinctId, String eventName, Map<String, Object> properties) {
        //单例初始化神策对象
        getSa();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
//                    LOGGER.info("神策埋点，distinctId:{}, eventName:{},properties:{}", distinctId, eventName, properties.toString());
                    sa.track(distinctId, eventName, properties);
                } catch (InvalidArgumentException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
    }


    public void addNoticePush(String userId,String ip,int user_type,String call,int notification_id,int number_of_notipeople,boolean is_sync,String platform,Boolean is_notification,String notification_range,boolean isFirst,Integer schoolId,String msgTime){
        Map<String, Object> properties = new HashMap();
        properties.put("user_type", getUserTypeName(user_type+""));
        properties.put("call",call);
        properties.put("notification_id", notification_id);
        properties.put("number_of_notipeople", number_of_notipeople);
        properties.put("is_sync", is_sync);
        properties.put("sending_platform", platform);
        properties.put("notification_test01", is_notification.toString());
        properties.put("notification_range", notification_range);
        properties.put("is_firsttime", isFirst);
        properties.put("school_id", schoolId+"");
        properties.put("msgtime", msgTime);

        getPreProperties(properties, null, ip);

        addSaTrack(userId, "notification", properties);
    }

    public void readNotice(String userId,String ip,int user_type,int notification_id,Boolean is_notification,int duration,int invitedType){
        Map<String, Object> properties = new HashMap<>();
        properties.put("user_type", getUserTypeName(user_type+""));
        properties.put("notification_id", notification_id);
        properties.put("notification_test01", is_notification.toString());
        properties.put("duration", duration);
        if(user_type==1) {
            properties.put("invited_type", invitedType == 1 ? "主账号" : "从账户");
        }

        getPreProperties(properties, null, ip);

        addSaTrack(userId, "read_notification", properties);
    }

    public void sendSms(String userId,int user_type,String call,int number_of_shortmespeople,Boolean is_notification){
        Map<String, Object> properties = new HashMap<>();
        properties.put("user_type", getUserTypeName(user_type+""));
        properties.put("call", call);
        properties.put("number_of_shortmespeople", number_of_shortmespeople);
        properties.put("notification_test01", is_notification.toString());

        getPreProperties(properties, null, "");

        addSaTrack(userId, "sending_shortmessage", properties);
    }

    public void praise(String userId,String ip,int user_type,int notification_id){
        Map<String, Object> properties = new HashMap<>();
        properties.put("user_type", getUserTypeName(user_type+""));
        properties.put("notification_id", notification_id);
        getPreProperties(properties, null, ip);
        addSaTrack(userId, "praise_notification", properties);
    }

    public void comment(String userId,String ip,int user_type,int notification_id){
        Map<String, Object> properties = new HashMap<>();
        properties.put("user_type", getUserTypeName(user_type+""));
        properties.put("notification_id", notification_id);
        getPreProperties(properties, null, ip);
        addSaTrack(userId, "comment_notification", properties);
    }

    @PreDestroy
    public void destory() {
        //销毁线程池
        executor.shutdown();
        //关闭神策对象
        sa.shutdown();
        LOGGER.info("SensorsDataUtil 销毁方法执行...");
    }


    /**
     * 拼装预置属性
     *
     * @param properties
     * @param sa         前端传过来的预置属性
     * @param ip         前端ip
     */
    private void getPreProperties(Map<String, Object> properties, JSONObject sa, String ip) {
        properties.put("$ip", ip);
        if (sa == null) {
            return;
        }

        for (Map.Entry<String, Object> entry : sa.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

    }

    private String getUserTypeName(String usertype) {
        String name = "";
        if ("2".equals(usertype)) {
            name = "教师";
        }
        else if("3".equals(usertype)){
            name = "园领导";
        }else{
            name = "家长";
        }

        return name;
    }

}
