package com.worthy.ly.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ly on 2018/1/22.
 */
public interface ICacheClient {
    String setex(String var1, int var2, String var3);

    String get(String var1);

    Long del(String var1);

    Long del(String... var1);

    Long expire(String var1, int var2);

    Long expireAt(String var1, long var2);

    Long ttl(String var1);

    boolean exists(String var1);

    Long incr(String var1);

    Long incrBy(String var1, long var2);

    Long decr(String var1);

    Long decrBy(String var1, long var2);

    Long lpush(String var1, String... var2);

    Long rpush(String var1, String... var2);

    Long lrem(String var1, long var2, String var4);

    Long llen(String var1);

    String lpop(String var1);

    String rpop(String var1);

    List<String> lrange(String var1, long var2, long var4);

    List<String> lrangeAll(String var1);

    Long hset(String var1, String var2, String var3);

    Long hsetnx(String var1, String var2, String var3);

    String hmset(String var1, Map<String, String> var2);

    String hget(String var1, String var2);

    List<String> hmget(String var1, String... var2);

    Boolean hexists(String var1, String var2);

    Long hdel(String var1, String... var2);

    Long hlen(String var1);

    Map<String, String> hgetAll(String var1);

    Long sadd(String var1, String... var2);

    Set<String> smembers(String var1);

    Long srem(String var1, String... var2);

    Long scard(String var1);

    Set<String> sunion(String... var1);

    Set<String> sdiff(String... var1);

    Long sdiffstore(String var1, String... var2);

    String setex(byte[] var1, int var2, byte[] var3);

    byte[] get(byte[] var1);

    Long del(byte[] var1);

    Long del(byte[]... var1);

    Long expire(byte[] var1, int var2);

    Long expireAt(byte[] var1, long var2);

    Long ttl(byte[] var1);

    boolean exists(byte[] var1);

    Long incr(byte[] var1);

    Long incrBy(byte[] var1, long var2);

    Long decr(byte[] var1);

    Long decrBy(byte[] var1, long var2);

    Long lpush(byte[] var1, byte[]... var2);

    Long rpush(byte[] var1, byte[]... var2);

    Long llen(byte[] var1);

    Long lrem(byte[] var1, long var2, byte[] var4);

    byte[] lpop(byte[] var1);

    byte[] rpop(byte[] var1);

    List<byte[]> lrange(byte[] var1, long var2, long var4);

    List<byte[]> lrangeAll(byte[] var1);

    Long hset(byte[] var1, byte[] var2, byte[] var3);

    Long hsetnx(byte[] var1, byte[] var2, byte[] var3);

    Long setnx(byte[] var1, byte[] var2);

    Long setnx(String var1, String var2);

    String hmset(byte[] var1, Map<byte[], byte[]> var2);

    byte[] hget(byte[] var1, byte[] var2);

    List<byte[]> hmget(byte[] var1, byte[]... var2);

    Boolean hexists(byte[] var1, byte[] var2);

    Long hdel(byte[] var1, byte[]... var2);

    Long hlen(byte[] var1);

    Map<byte[], byte[]> hgetAll(byte[] var1);

    Long sadd(byte[] var1, byte[]... var2);

    Set<byte[]> smembers(byte[] var1);

    Long srem(byte[] var1, byte[]... var2);

    Long scard(byte[] var1);

    Set<byte[]> sunion(byte[]... var1);

    Set<byte[]> sdiff(byte[]... var1);

    Long sdiffstore(byte[] var1, byte[]... var2);

    Long hincrBy(String var1, String var2, long var3);

    Double incrByFloat(String var1, double var2);

    Double hincrByFloat(String var1, String var2, double var3);

    Long zadd(String var1, double var2, String var4);

    Long zadd(String var1, Map<String, Double> var2);

    Long zcount(String var1, double var2, double var4);

    Long zcount(String var1, String var2, String var3);

    Double zincrby(String var1, double var2, String var4);

    Set<String> zrange(String var1, long var2, long var4);

    Set<String> zrangeByScore(String var1, double var2, double var4);

    Set<String> zrangeByScore(String var1, String var2, String var3);

    Set<String> zrangeByScore(String var1, double var2, double var4, int var6, int var7);

    Set<String> zrevrange(String var1, long var2, long var4);

    Set<String> zrevrangeByScore(String var1, double var2, double var4);

    Set<String> zrevrangeByScore(String var1, String var2, String var3);

    Set<String> zrevrangeByScore(String var1, double var2, double var4, int var6, int var7);

    Long zrevrank(String var1, String var2);

    Long zrem(String var1, String... var2);

    Long zremrangeByRank(String var1, long var2, long var4);

    Long zremrangeByScore(String var1, double var2, double var4);

    Long zremrangeByScore(String var1, String var2, String var3);

    String setObjectEx(byte[] var1, int var2, Object var3);

    Object getObject(byte[] var1);

    Boolean sismember(String var1, String var2);
}
