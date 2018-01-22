package conf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@DisconfFile(filename = "schoolnotice.properties")
@DisconfUpdateService(classes = {SchoolnoticeConf.class})
public class SchoolnoticeConf implements IDisconfUpdate{
    protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolnoticeConf.class);
    
    //TODO  add your buss fileds
    private String host;

    private String pushAliasPrefix;

    private String javaPortUrl;

    private String mqAuthKey;

    private String mqAuthUrl;

    //神策地址
    private String saServerUrl;

    //推送地址
    private String pushRequestUrl;

    private String h5SmsUrl;

    private  String phpNoticeUrl;

    private String activityContent;

    private String freeSchoolIds;

    private String pushNoticeMqKey;

    private String notifyAchieveMqKey;

    private String checkSchoolIds;

    private static String authUrl;

    private static String bizKey;

    @DisconfFileItem(name = "schoolnotice.host", associateField = "host")
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    @DisconfFileItem(name = "push.alias.prefix", associateField = "pushAliasPrefix")
    public String getPushAliasPrefix() {
        return pushAliasPrefix;
    }

    public void setPushAliasPrefix(String pushAliasPrefix) {
        this.pushAliasPrefix = pushAliasPrefix;
    }

    @DisconfFileItem(name = "push.request.url", associateField = "pushRequestUrl")
    public String getPushRequestUrl() {
        return pushRequestUrl;
    }

    public void setPushRequestUrl(String pushRequestUrl) {
        this.pushRequestUrl = pushRequestUrl;
    }

    @DisconfFileItem(name = "javaport.url", associateField = "javaPortUrl")
    public String getJavaPortUrl() {
        return javaPortUrl;
    }

    public void setJavaPortUrl(String javaPortUrl) {
        this.javaPortUrl = javaPortUrl;
    }

    public void reload() throws Exception {
    		//TODO your buss
        LOGGER.debug("host: " + host);
    }

    @DisconfFileItem(name = "mq.auth.key", associateField = "mqAuthKey")
    public String getMqAuthKey() {
        return mqAuthKey;
    }


    public void setMqAuthKey(String mqAuthKey) {
        this.mqAuthKey = mqAuthKey;
    }

    @DisconfFileItem(name = "mq.auth.url", associateField = "mqAuthUrl")
    public String getMqAuthUrl() {
        return mqAuthUrl;
    }


    public void setMqAuthUrl(String mqAuthUrl) {
        this.mqAuthUrl = mqAuthUrl;
    }

    public String getH5SmsUrl() {
        return h5SmsUrl;
    }

    @DisconfFileItem(name = "h5.sms.url", associateField = "h5SmsUrl")
    public void setH5SmsUrl(String h5SmsUrl) {
        this.h5SmsUrl = h5SmsUrl;
    }

    public String getPhpNoticeUrl() {
        return phpNoticeUrl;
    }

    @DisconfFileItem(name = "php.notice.url", associateField = "phpNoticeUrl")
    public void setPhpNoticeUrl(String phpNoticeUrl) {
        this.phpNoticeUrl = phpNoticeUrl;
    }

    @DisconfFileItem(name = "sa.server.url", associateField = "saServerUrl")
    public String getSaServerUrl() {
        return saServerUrl;
    }

    public void setSaServerUrl(String saServerUrl) {
        this.saServerUrl = saServerUrl;
    }

    @DisconfFileItem(name = "activity.content", associateField = "activityContent")
    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @DisconfFileItem(name = "auth_url", associateField = "authUrl")
    public static String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    @DisconfFileItem(name = "biz_key", associateField = "bizKey")
    public static String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    @DisconfFileItem(name = "school.free.ids",associateField = "freeSchoolIds")
    public String getFreeSchoolIds() {
        return freeSchoolIds;
    }

    public void setFreeSchoolIds(String freeSchoolIds) {
        this.freeSchoolIds = freeSchoolIds;
    }

    @DisconfFileItem(name = "mq.push.notice.key",associateField = "pushNoticeMqKey")
    public String getPushNoticeMqKey() {
        return pushNoticeMqKey;
    }

    public void setPushNoticeMqKey(String pushNoticeMqKey) {
        this.pushNoticeMqKey = pushNoticeMqKey;
    }

    @DisconfFileItem(name = "mq.notify.achieve.key",associateField = "notifyAchieveMqKey")
    public String getNotifyAchieveMqKey() {
        return notifyAchieveMqKey;
    }

    public void setNotifyAchieveMqKey(String notifyAchieveMqKey) {
        this.notifyAchieveMqKey = notifyAchieveMqKey;
    }

    @DisconfFileItem(name = "school.check.ids",associateField = "checkSchoolIds")
    public String getCheckSchoolIds() {
        return checkSchoolIds;
    }

    public void setCheckSchoolIds(String checkSchoolIds) {
        this.checkSchoolIds = checkSchoolIds;
    }
}
