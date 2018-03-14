//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.conf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@DisconfFile(
        filename = "ali-oss.properties"
)
@DisconfUpdateService(
        classes = {OssConf.class}
)
public class OssConf implements IDisconfUpdate {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OssConf.class);
    protected static final Pattern pattern = Pattern.compile("\\w+(K|M|G)?B$", 2);
    private static String domain;
    private static String bucketName;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String roleArn;
    private static String endpoint;
    private static String maxSize;

    public OssConf() {
    }

    @Override
    public void reload() throws Exception {
        LOGGER.info("OSS: reload ...");
    }

    @DisconfFileItem(
            name = "oss.domain",
            associateField = "domain"
    )
    public static String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        domain = domain;
    }

    @DisconfFileItem(
            name = "oss.bucketName",
            associateField = "bucketName"
    )
    public static String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        bucketName = bucketName;
    }

    @DisconfFileItem(
            name = "oss.accessKeyId",
            associateField = "accessKeyId"
    )
    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        accessKeyId = accessKeyId;
    }

    @DisconfFileItem(
            name = "oss.accessKeySecret",
            associateField = "accessKeySecret"
    )
    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        accessKeySecret = accessKeySecret;
    }

    @DisconfFileItem(
            name = "oss.roleArn",
            associateField = "roleArn"
    )
    public static String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        roleArn = roleArn;
    }

    @DisconfFileItem(
            name = "oss.endpoint",
            associateField = "endpoint"
    )
    public static String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        endpoint = endpoint;
    }

    @DisconfFileItem(
            name = "oss.maxSize",
            associateField = "maxSize"
    )
    public static String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        maxSize = maxSize;
    }

    public static long getUploadMaxSize() {
        Matcher matcher = pattern.matcher(maxSize);
        long ms = 0L;
        if(matcher.matches()) {
            ms = Long.valueOf(maxSize.replaceAll("(K|M|G)?B$", "")).longValue();
        }

        if(maxSize.toLowerCase().endsWith("kb")) {
            ms *= 1024L;
        } else if(maxSize.toLowerCase().endsWith("mb")) {
            ms = ms * 1024L * 1024L;
        } else if(maxSize.toLowerCase().endsWith("gb")) {
            ms = ms * 1024L * 1024L * 1024L;
        }

        return ms;
    }
}
