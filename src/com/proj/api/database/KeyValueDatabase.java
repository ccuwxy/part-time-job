package com.proj.api.database;

import com.proj.api.exception.database.NonRelationalDatabaseException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class KeyValueDatabase {
    private static JedisPool pool = JedisPoolUtils.getPool();
    private String sPrefix;

    private Jedis redis = null;

    public KeyValueDatabase(String _sPrefix) throws NonRelationalDatabaseException {
        try {
            redis = KeyValueDatabase.pool.getResource();
            this.sPrefix = _sPrefix;
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public KeyValueDatabase() throws NonRelationalDatabaseException {
        try {
            redis = KeyValueDatabase.pool.getResource();
            this.sPrefix = "";
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void setPrefix(String _sPrefix) {
        this.sPrefix = _sPrefix;
    }

    public void set(String _sKey, String _sValue) throws NonRelationalDatabaseException {
        try {
            redis.set(_sKey, _sValue);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void set(String _sKey, String _sValue, int _iExpire) throws NonRelationalDatabaseException {
        try {
            this.set(this.sPrefix + _sKey, _sValue);
            redis.expire(this.sPrefix + _sKey, _iExpire);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void del(String _sKey) throws NonRelationalDatabaseException {
        try {
            redis.del(this.sPrefix + _sKey);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public String get(String _sKey) throws NonRelationalDatabaseException {
        try {
            return redis.get(this.sPrefix + _sKey);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public boolean exists(String _sKey) throws NonRelationalDatabaseException {
        try {
            return redis.exists(this.sPrefix + _sKey);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void expire(String _sKey, int _iExpire) throws NonRelationalDatabaseException {
        try {
            redis.expire(this.sPrefix + _sKey, _iExpire);
        } catch (JedisException e) {
            close();
            throw new NonRelationalDatabaseException(e);
        }
    }

    public void close() throws NonRelationalDatabaseException {
        try {
            KeyValueDatabase.pool.returnResource(this.redis);
            redis = null;
        } catch (JedisException e) {
            throw new NonRelationalDatabaseException(e);
        }
    }
}
