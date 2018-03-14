//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import javax.activation.MimetypesFileTypeMap;

import com.worthy.ueditor.conf.OssConf;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OssUtil {
    private static final Logger logger = LoggerFactory.getLogger(OssUtil.class);
    static final String domain = OssConf.getDomain();
    static final String bucketName = OssConf.getBucketName();
    static final String accessKeyId = OssConf.getAccessKeyId();
    static final String accessKeySecret = OssConf.getAccessKeySecret();
    static final String roleArn = OssConf.getRoleArn();
    static final String endpoint = OssConf.getEndpoint();
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    public OssUtil() {
    }

    public static PutObjectResult uploadImg(String path, File file) throws Exception {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.length());
        String contentType = (new MimetypesFileTypeMap()).getContentType(file);
        meta.setContentType(contentType);
        return uploadImg(new PutObjectRequest(bucketName, path, file, meta));
    }

    public static PutObjectResult uploadImg(String path, File file, ObjectMetadata meta) throws Exception {
        return uploadImg(new PutObjectRequest(bucketName, path, file, meta));
    }

    public static PutObjectResult uploadImg(String path, InputStream stream, ObjectMetadata meta) throws Exception {
        return uploadImg(new PutObjectRequest(bucketName, path, stream, meta));
    }

    public static PutObjectResult uploadImg(PutObjectRequest putObjectRequest) throws Exception {
        OSSClient client = new OSSClient(accessKeyId, accessKeySecret);
        boolean exists = client.doesBucketExist(bucketName);
        if(!exists) {
            client.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicReadWrite);
            client.createBucket(createBucketRequest);
        }

        PutObjectResult putObject = client.putObject(putObjectRequest);
        logger.info("OSS uploadImg CRC, ETag: " + putObject.getClientCRC() + ", " + putObject.getETag());
        return putObject;
    }

    public static Token makeTokenPolicy() {
        try {
            long expireTime = 900L;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000L;
            Token temp = makeToken(Long.valueOf(expireTime));
            String accessId = temp.getAccessKeyId();
            String accessKey = temp.getAccessKeySecret();
            String bucket = bucketName;
            String dir = bucketName;
            String host = "http://" + bucket + "." + endpoint;
            OSSClient client = new OSSClient(endpoint, accessId, accessKey);
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem("content-length-range", 0L, 1048576000L);
            policyConds.addConditionItem(MatchMode.StartWith, "key", dir);
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            Token token = new Token();
            token.setAccessKeyId(accessId);
            token.setAccessKeySecret(accessKey);
            token.setSecurityToken(temp.getSecurityToken());
            token.setSignature(postSignature);
            token.setPolicy(encodedPolicy);
            token.setDir(dir);
            token.setHost(host);
            token.setExpiration(temp.getExpiration());
            token.setExpirationTime(Long.valueOf(expireEndTime));
            logger.info("success to get a token.");
            return token;
        } catch (UnsupportedEncodingException var18) {
            logger.error("update file fail.", var18);
            throw new RuntimeException(var18);
        }
    }

    public static Token makeToken() {
        return makeToken(Long.valueOf(900L));
    }

    public static Token makeToken(Long DurationSeconds) {
        return makeToken(accessKeyId, accessKeySecret, roleArn, bucketName, (String)null, DurationSeconds);
    }

    public static Token makeToken(String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy, Long DurationSeconds) {
        Long expirationTime = Long.valueOf(Calendar.getInstance().getTimeInMillis() + DurationSeconds.longValue() * 1000L);
        if(StringUtils.isBlank(policy)) {
            policy = "{\n    \"Version\": \"1\", \n    \"Statement\": [\n        {\n            \"Action\": [\n                \"oss:GetBucket\", \n                \"oss:GetObject\", \n                \"oss:PutObject\", \n                \"oss:PutObjectAcl\", \n                \"oss:AbortMultipartUpload\", \n                \"oss:PostObject\" \n            ], \n            \"Resource\": [\n                \"acs:oss:*:*:*\"\n            ], \n            \"Effect\": \"Allow\"\n        }\n    ]\n}";
        }

        ProtocolType protocolType = ProtocolType.HTTPS;

        try {
            AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy, protocolType, DurationSeconds);
            Token token = new Token();
            token.setAccessKeyId(response.getCredentials().getAccessKeyId());
            token.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            token.setSecurityToken(response.getCredentials().getSecurityToken());
            token.setExpiration(response.getCredentials().getExpiration());
            token.setExpirationTime(expirationTime);
            logger.info("success to get a token.");
            return token;
        } catch (ClientException var10) {
            logger.info("Failed to get a token.");
            logger.info("Error code: " + var10.getErrCode());
            logger.info("Error message: " + var10.getErrMsg());
            throw new RuntimeException("Failed to get a token.");
        }
    }

    private static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy, ProtocolType protocolType, Long DurationSeconds) throws ClientException {
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion("2015-04-01");
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(DurationSeconds);
            AssumeRoleResponse response = (AssumeRoleResponse)client.getAcsResponse(request);
            return response;
        } catch (ClientException var11) {
            throw var11;
        }
    }

    public static String getDomain() {
        return domain;
    }

    public static String getBucketName() {
        return bucketName;
    }
}
