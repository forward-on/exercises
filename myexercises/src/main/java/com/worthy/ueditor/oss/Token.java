//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.oss;

import java.io.Serializable;

public class Token implements Serializable {
    private static final long serialVersionUID = 3152100896535908524L;
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String expiration;
    private Long expirationTime;
    private String policy;
    private String dir;
    private String host;
    private String signature;

    public Token() {
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Long getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getDir() {
        return this.dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Token{\n accessKeyId:'" + this.accessKeyId + '\'' + ",\n accessKeySecret:'" + this.accessKeySecret + '\'' + ",\n securityToken:'" + this.securityToken + '\'' + ",\n signature:" + this.signature + ",\n expiration:'" + this.expiration + '\'' + ",\n expirationTime:" + this.expirationTime + ",\n policy:'" + this.policy + '\'' + ",\n dir:'" + this.dir + '\'' + ",\n host:'" + this.host + '\'' + '}';
    }
}
