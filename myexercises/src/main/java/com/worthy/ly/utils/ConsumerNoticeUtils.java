package com.worthy.ly.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.bbtree.mq.ClientFactory;
//import com.bbtree.mq.IMqClient;
//import com.bbtree.schoolnotice.api.vo.AchieveNotifyVo;
//import com.bbtree.schoolnotice.api.vo.PushNoticeVo;
//import com.bbtree.schoolnotice.core.handler.AchieveNoticeHandler;
//import com.bbtree.schoolnotice.core.handler.PushNoticeByRead;
import conf.SchoolnoticeConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DependsOn("schoolnoticeConf")
@Component
public class ConsumerNoticeUtils {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerNoticeUtils.class);

    @Qualifier(value = "schoolnoticeConf")
    @Autowired
    private SchoolnoticeConf schoolnoticeConf;

//    @Autowired
//    private PushNoticeByRead pushNoticeByRead;

//    @Autowired
//    private AchieveNoticeHandler achieveNoticeHandler;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void  init(){
        consumerPushNotice();
        consumerAchieveNotify();
    }

    private void consumerAchieveNotify(){
        try {
//            IMqClient client = ClientFactory.getClient(schoolnoticeConf.getNotifyAchieveMqKey(), schoolnoticeConf.getMqAuthUrl()); // 服务认证地址
            // 消费实现
            executor.execute(() -> {
                try {
//                    client.consume(
//                            (routingKey, contentType, message) -> {
//                                try {
//                                    List<AchieveNotifyVo> notifyVos  = JSONArray.parseArray(message, AchieveNotifyVo.class);
//                                    achieveNoticeHandler.notify(notifyVos);
//                                }catch (Exception e){
//                                    logger.error("获取回执消息队列处理消息异常，e=" + e.getMessage());
//                                }
//                                return true;
//                            });
                }catch (Exception e){
                    logger.error("获取回执服务获取消息队列异常，e={}",e);
                }
            });
        } catch (Exception e) {
            logger.error("获取回执服务队列客户端出现异常，e=" + e.getMessage());
        }
    }

    public void consumerPushNotice() {
            try {
//                IMqClient client = ClientFactory.getClient(schoolnoticeConf.getPushNoticeMqKey(), schoolnoticeConf.getMqAuthUrl()); // 服务认证地址
                // 消费实现
//                executor.execute(() -> {
//                        try {
//                            client.consume(
//                                    (routingKey, contentType, message) -> {
//                                        try {
//                                            PushNoticeVo pushNoticeVo = JSONObject.parseObject(message, PushNoticeVo.class);
//                                            logger.info("获取消息队列中通知内容:"+JSONObject.toJSONString(pushNoticeVo));
//                                            pushNoticeByRead.pushNoticeRead(pushNoticeVo.getRange(), pushNoticeVo.getClassIds(), pushNoticeVo.paresClassWithUser(), pushNoticeVo.getSchoolId(), new Date(), pushNoticeVo.getOwnerId(), pushNoticeVo.getNoticeId(), pushNoticeVo.getContentNotice(), pushNoticeVo.getRole(), pushNoticeVo.getEndDate(), pushNoticeVo.isAttr(), pushNoticeVo.getIp(), pushNoticeVo.getPlatform(), pushNoticeVo.isAll(), pushNoticeVo.isGroup(), pushNoticeVo.getTimeType(),true);
//                                        }catch (Exception e){
//                                            logger.error("获取消息队列处理消息异常，e={}",e);
//                                        }
//                                        return true;
//                                    });
//                        }catch (Exception e){
//                            logger.error("获取推送服务获取消息队列异常，e={}",e);
//                        }
//                });
            } catch (Exception e) {
                logger.error("获取推送服务队列客户端出现异常，e=" + e.getMessage());
            }

    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
