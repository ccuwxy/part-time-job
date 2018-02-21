package com.proj.api.database;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class JedisPoolUtils {
    private static redis.clients.jedis.JedisPool pool = null;

    public static redis.clients.jedis.JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(50);
            config.setTestOnBorrow(true);
            pool = new redis.clients.jedis.JedisPool(config, "127.0.0.1", 6379);
        }
        return pool;
    }
}
