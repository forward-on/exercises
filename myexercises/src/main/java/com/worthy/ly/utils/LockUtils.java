package com.worthy.ly.utils;

//import com.bbtree.cache.ICacheClient;
//import com.bbtree.schoolnotice.com.worthy.ly.conf.SchoolnoticeConf;
//import com.bbtree.schoolnotice.core.cache.SchoolNoticeCache;

public class LockUtils {

    public static boolean isLock(String lockKey,Integer sec){
        boolean isLock = false;
//        try {
//            ICacheClient cacheClient = SchoolNoticeCache.getClient(SchoolnoticeConf.getBizKey());
//            Long nx = cacheClient.setnx(lockKey, "1");
//            if (nx == 0) {
//                isLock = true;
//            } else {
//                cacheClient.expire(lockKey, sec);
//            }
//        }catch (Exception e){
//
//        }
        return isLock;
    }

    public static void unLock(String lockKey){
//        ICacheClient cacheClient = SchoolNoticeCache.getClient(SchoolnoticeConf.getBizKey());
//        cacheClient.del(lockKey);
    }


}
