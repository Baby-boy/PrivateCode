package com.glanway.hr.sso.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {

	// 如果Spring容器中有该对象就注入，没有就忽略
	@Autowired(required = false)
	private ShardedJedisPool shardedJedisPool;

	private <T> T execute(Function<T, ShardedJedis> fun) {
		ShardedJedis shardedJedis = null;
		try {
			// 从连接池中获取到jedis分片对象
			shardedJedis = shardedJedisPool.getResource();
			return fun.callback(shardedJedis);
		} finally {
			if (null != shardedJedis) {
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				shardedJedis.close();
			}
		}
	}

	public String set(final String key, final String value) {
		return this.execute(new Function<String, ShardedJedis>() {
			@Override
			public String callback(ShardedJedis e) {
				return e.set(key, value);
			}
		});
	}

	public String get(final String key) {
		return this.execute(new Function<String, ShardedJedis>() {
			@Override
			public String callback(ShardedJedis e) {
				return e.get(key);
			}
		});
	}

	public Long del(final String key) {
		return this.execute(new Function<Long, ShardedJedis>() {
			@Override
			public Long callback(ShardedJedis e) {
				return e.del(key);
			}
		});
	}

	public Long expire(final String key, final Integer seconds) {
		return this.execute(new Function<Long, ShardedJedis>() {
			@Override
			public Long callback(ShardedJedis e) {
				return e.expire(key, seconds);
			}
		});
	}

	public String set(final String key, final String value, final Integer seconds) {
		return this.execute(new Function<String, ShardedJedis>() {
			@Override
			public String callback(ShardedJedis e) {
				String str = e.set(key, value);
				e.expire(key, seconds);
				return str;
			}
		});
	}

	public Long hset(final String key, final String field, final String value, final Integer seconds) {
		return this.execute(new Function<Long, ShardedJedis>() {
			@Override
			public Long callback(ShardedJedis e) {
				Long _long = e.hset(key, field, value);
				e.expire(key, seconds);
				return _long;
			}
		});
	}

	public Long hset(final String key, final String field, final String value) {
		return this.execute(new Function<Long, ShardedJedis>() {
			@Override
			public Long callback(ShardedJedis e) {
				return e.hset(key, field, value);
			}
		});
	}

	public String hget(final String key, final String field) {
		return this.execute(new Function<String, ShardedJedis>() {
			@Override
			public String callback(ShardedJedis e) {
				return e.hget(key, field);
			}
		});
	}

	public Map<String, String> hgetAll(final String key) {
		return this.execute(new Function<Map<String, String>, ShardedJedis>() {
			@Override
			public Map<String, String> callback(ShardedJedis e) {
				return e.hgetAll(key);
			}
		});
	}

	public Long hdel(final String key, final String field) {
		return this.execute(new Function<Long, ShardedJedis>() {
			@Override
			public Long callback(ShardedJedis e) {
				return e.hdel(key, field);
			}
		});
	}
}
