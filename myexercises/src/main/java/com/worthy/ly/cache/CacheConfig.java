package com.worthy.ly.cache;

/**
 * Created by ly on 2018/1/22.
 */
public class CacheConfig {
    private String businessKey;
    private String bizCode;
    private String authUrl;
    private String configPath;
    private String zkAdress;
//    private JedisConfig jedisConfig;

    public CacheConfig() {
    }

    public CacheConfig(String bizKey, String authUrl, String configPath) {
        this.businessKey = bizKey;
        this.authUrl = authUrl;
        this.configPath = configPath;
    }

    public String getBizCode() {
        return this.bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBusinessKey() {
        return this.businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getAuthUrl() {
        return this.authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getConfigPath() {
        return this.configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getZkAdress() {
        return this.zkAdress;
    }

    public void setZkAdress(String zkAdress) {
        this.zkAdress = zkAdress;
    }

//    public JedisConfig getJedisConfig() {
//        return this.jedisConfig;
//    }
//
//    public void setJedisConfig(JedisConfig jedisConfig) {
//        this.jedisConfig = jedisConfig;
//
}
