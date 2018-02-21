package com.proj.api.database;

import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.utils.RandomUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by jangitlau on 2017/11/15.
 */
public class KeyValueQueue {
    private static JedisPool pool = JedisPoolUtils.getPool();
    private String sQueueName;
    private Jedis redis = null;

    public KeyValueQueue(String _sQueueName) throws NonRelationalDatabaseException {
        try {
            redis = pool.getResource();
            this.sQueueName = _sQueueName;
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void setQueueName(String _sQueueName) {
        this.sQueueName = _sQueueName;
    }

    public void close() throws NonRelationalDatabaseException {
        try {
            pool.returnResource(this.redis);
            redis = null;
        } catch (JedisException e) {
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void pushKey(String _sKey) throws NonRelationalDatabaseException {
        try {
            this.redis.lpush(sQueueName,_sKey);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public String popKey() throws NonRelationalDatabaseException {
        try {
            return this.redis.lpop(sQueueName);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void pushValue(String _sValue) throws NonRelationalDatabaseException {
        try {
            String sKey = sQueueName + "_" + RandomUtils.getUUID();
            this.redis.lpush(sQueueName, sKey);
            this.redis.set(sKey,_sValue);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public String popValue() throws NonRelationalDatabaseException {
        try {
            String sKey = this.redis.lpop(sQueueName);
            if(sKey==null){
                return null;
            }else{
                return this.redis.get(sKey);
            }
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public int getLength() throws NonRelationalDatabaseException {
        try {
            return this.redis.llen(sQueueName).intValue();
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }
}
