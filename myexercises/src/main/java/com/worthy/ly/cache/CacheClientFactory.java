package com.worthy.ly.cache;

import com.worthy.ly.service.ICacheClient;

import com.alibaba.fastjson.JSON;
//import com.bbtree.cache.jedis.ClientProxy;
//import com.bbtree.cache.jedis.ClientTimeProxy;
//import com.bbtree.cache.jedis.JedisClient;
//import com.bbtree.cache.jedis.JedisClusterClient;
//import com.bbtree.cache.jedis.JedisConfig;
//import com.bbtree.paas.auth.service.AuthClientFactory;
//import com.bbtree.paas.auth.service.IAuthClient;
//import com.bbtree.paas.auth.vo.AuthDescriptor;
//import com.bbtree.paas.auth.vo.AuthResult;
//import com.bbtree.paas.zk.zookeeper.ZKClient;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheClientFactory {

    /*
    private static final String PATH_PRE = "/bbtree/ts/";
    private static final String SEPARATOR = "/";
    protected static final Logger log = LoggerFactory.getLogger(CacheClientFactory.class);
    private static Map<String, ICacheClient> caches = new ConcurrentHashMap();
    private static Map<String, CacheConfig> configs = new ConcurrentHashMap();
    private static Map<String, ZKClient> zkClients = new ConcurrentHashMap();

    public CacheClientFactory() {
        }

    public static ICacheClient getClient(String serviceId, String authUrl) {
        ICacheClient client = (ICacheClient)caches.get(serviceId);
        if(client == null) {
            client = initClient(serviceId, authUrl);
        }

        log.debug(" ---> get client result: {}", client);
        return client;
    }

    private static synchronized ICacheClient initClient(String bizKey, String authUrl) {
        log.info(" ---> init cache client ...");
        ICacheClient client = (ICacheClient)caches.get(bizKey);
        if(client == null) {
            CacheConfig config = getCacheConfig(bizKey, authUrl);
            client = createClient(config);
        }

        log.info(" ---> init cache client down: {}", client);
        return client;
    }

    private static ICacheClient createClient(CacheConfig config) {
        ICacheClient client = null;
        if(config != null) {
            Object client;
            if(config.getJedisConfig().getServerArray().length > 1) {
                client = new JedisClusterClient(config.getJedisConfig());
            } else {
                client = new JedisClient(config.getJedisConfig());
            }

            client = ClientTimeProxy.getProxy((ICacheClient)client, config);
            caches.put(config.getBusinessKey(), client);
        }

        return client;
    }

    private static CacheConfig getCacheConfig(String bizKey, String authUrl) {
        log.info(" ---> init cache config ...");
        CacheConfig config = (CacheConfig)configs.get(bizKey);
        if(config == null) {
            String[] arr = bizKey.split("-");
            String configPath = "/bbtree/ts/" + arr[0] + "/" + arr[1] + "/" + bizKey;
            CacheConfig newConfig = new CacheConfig(bizKey, authUrl, configPath);
            newConfig.setBizCode(arr[0]);

            try {
                JedisConfig jc = getJedisConfig(newConfig);
                if(jc != null) {
                    newConfig.setJedisConfig(jc);
                    configs.put(bizKey, newConfig);
                    config = newConfig;
                }
            } catch (Exception var7) {
                log.error(" ---> init cache config error. ", var7);
            }
        }

        log.info(" ---> init cache config: {}", JSON.toJSONString(config));
        return config;
    }

    private static JedisConfig getJedisConfig(CacheConfig cacheConfig) throws Exception {
        log.info(" ---> authorize cache config, key: {}, url: {}", cacheConfig.getBusinessKey(), cacheConfig.getAuthUrl());
        String result = doAuth(cacheConfig);
        cacheConfig.setZkAdress(result);
        log.info(" ---> authorize result: {}", result);
        if(result != null && result.trim().length() != 0) {
            ZKClient zkClient = new ZKClient(result, 3000, new String[0]);
            zkClients.put(cacheConfig.getBusinessKey(), zkClient);
            String zkConfig = zkClient.getNodeData(cacheConfig.getConfigPath(), new CacheClientFactory.CacheZkWatcher(cacheConfig));
            log.info(" ---> jedis zk config: {}", zkConfig);
            return parseConfig(zkConfig);
        } else {
            return null;
        }
    }

    private static String doAuth(CacheConfig config) throws Exception {
        IAuthClient iauth = AuthClientFactory.getAuthClient();
        AuthDescriptor ad = new AuthDescriptor(config.getAuthUrl(), config.getBusinessKey());
        AuthResult ar = iauth.auth(ad);
        String result = ar.getZkAdress();
        return result;
    }

    private static JedisConfig parseConfig(String config) {
        JedisConfig conf = (JedisConfig)JSON.parseObject(config, JedisConfig.class);
        if(log.isDebugEnabled()) {
            log.debug(" ---> jedis parseConfig: {}", JSON.toJSON(conf));
        }

        return conf;
    }

    private static void resetCache(CacheConfig cacheConfig) {
        CacheConfig cc = (CacheConfig)configs.get(cacheConfig.getBusinessKey());
        if(cc != null) {
            try {
                ZKClient zkClient = (ZKClient)zkClients.get(cacheConfig.getBusinessKey());
                String zkConfig = zkClient.getNodeData(cacheConfig.getConfigPath(), new CacheClientFactory.CacheZkWatcher(cacheConfig));
                log.info(" ---> reset jedis zk config: {}", zkConfig);
                JedisConfig oldJc = cc.getJedisConfig();
                JedisConfig jc = parseConfig(zkConfig);
                cacheConfig.setJedisConfig(jc);
                log.info(" ---> reset jedis config, new: [{}], old: [{}]", jc, oldJc);
                ICacheClient oldClient = (ICacheClient)caches.get(cacheConfig.getBusinessKey());
                ICacheClient client = createClient(cacheConfig);
                log.info(" ---> reset jedis client, new: [{}], old: [{}]", client, oldClient);
                if(client != null && oldClient != null && oldClient instanceof ClientProxy) {
                    ICacheClient temp = ((ClientProxy)oldClient).getClient();
                    if(temp instanceof JedisClient) {
                        ((JedisClient)temp).destroy();
                    }
                }
            } catch (Exception var9) {
                log.error(" ---> reset cache error. ", var9);
            }

        }
    }

    private static class CacheZkWatcher implements Watcher {
        private CacheConfig cacheConfig;

        CacheZkWatcher(CacheConfig cacheConfig) {
            this.cacheConfig = cacheConfig;
        }

        public void process(WatchedEvent event) {
            CacheClientFactory.log.info(" --->  watch zk process.{};{};{}", new Object[]{event.getPath(), event.getState(), event.getType()});
            if(KeeperState.Expired.equals(event.getState())) {
                this.processExpired();
            } else if(EventType.NodeDataChanged.equals(event.getType()) && this.cacheConfig.getConfigPath().equals(event.getPath())) {
                CacheClientFactory.resetCache(this.cacheConfig);
            }

        }

        private void processExpired() {
            ZKClient zkClient = (ZKClient)CacheClientFactory.zkClients.get(this.cacheConfig.getBusinessKey());

            try {
                for(; !zkClient.isConnected(); TimeUnit.SECONDS.sleep(1L)) {
                    try {
                        zkClient.retryConnection();
                    } catch (IllegalStateException var3) {
                        CacheClientFactory.log.error("retry connection zk.", var3);
                        break;
                    } catch (Exception var4) {
                        CacheClientFactory.log.error("retry connection zk failed.", var4);
                    }
                }

                CacheClientFactory.resetCache(this.cacheConfig);
            } catch (Exception var5) {
                CacheClientFactory.log.error("retry connection zk failed.", var5);
            }

        }
    }
*/}
