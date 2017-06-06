package com.tadpole.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 说明: redis工具类
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年3月1日 下午6:20:26
 * @Version:1.0
 */
public abstract class JedisUtil {

	private static String REDIS_HOST;

	private static int REDIS_PORT;

	private static String REDIS_PASSWORD;

	private static JedisPool jedisPool;
	static {
		try {
			// 读取本地配置文件
			InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			REDIS_HOST = properties.getProperty("spring.redis.host");
			REDIS_PORT = Integer.valueOf(properties.getProperty("spring.redis.port"));
			REDIS_PASSWORD = properties.getProperty("spring.redis.password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 构建连接池配置信息
		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(50);// 设置最大连接数
		config.setMaxIdle(256);// 最大空闲连接数
		config.setTestOnBorrow(true);// 在获取连接的时候检查有效性,默认false
		config.setTestOnReturn(true);// 在返回连接的时候检查有效性,默认false
		config.setTestWhileIdle(true);// 在空闲时检查有效性,默认false
		config.setMinEvictableIdleTimeMillis(60000L);// 对象空闲多久后逐出
		config.setTimeBetweenEvictionRunsMillis(3000L);// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setNumTestsPerEvictionRun(-1);// 每次逐出检查时,逐出的最大数目,如果为负数就是 : 1/abs(n), 默认3

		jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT, 60000);
	}

	/**
	 * 说明: 执行方法(公用代码提取)
	 * 
	 * @param function
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年3月1日 下午1:13:20
	 */
	public static <T> T execute(Function<T, Jedis> function) {
		Jedis jedis = null;
		try {
			// 从连接池中获取到jedis对象
			jedis = jedisPool.getResource();
			// 设置redis密码
			if (StringUtils.isNotEmpty(REDIS_PASSWORD)) {
				jedis.auth(REDIS_PASSWORD);
			}
			return function.callback(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != jedis) {
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 说明: 设置redis时间
	 * 
	 * @param key(key值)
	 * @param seconds(时间,秒计)
	 * @return
	 */
	public static Long expire(final String key, final Integer seconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	/**
	 * 说明: 设置redis时间
	 * 
	 * @param key(key值)
	 * @param seconds(时间,秒计)
	 * @return
	 */
	public static Long expire(final byte[] key, final Integer seconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	/**
	 * 说明: 设置redis时间
	 * 
	 * @param key(key值)
	 * @param seconds(时间,毫秒计)
	 * @return
	 */
	public static Long pexpire(final String key, final Long milliseconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.pexpire(key, milliseconds);
			}
		});
	}

	/**
	 * 说明: 设置redis时间
	 * 
	 * @param key(key值)
	 * @param seconds(时间,毫秒计)
	 * @return
	 */
	public static Long pexpire(final byte[] key, final Long milliseconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.pexpire(key, milliseconds);
			}
		});
	}

	public static String set(final String key, final String value) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	public static String set(final String key, final String value, final Integer seconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.set(key, value);
				expire(key, seconds);
				return str;
			}
		});
	}

	public static String set(final String key, final String value, final Long milliseconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.set(key, value);
				pexpire(key, milliseconds);
				return str;
			}
		});
	}

	public static String set(final byte[] key, final byte[] value) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	public static String set(final byte[] key, final byte[] value, final Integer seconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.set(key, value);
				expire(key, seconds);
				return str;
			}
		});
	}

	public static String set(final byte[] key, final byte[] value, final Long milliseconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.set(key, value);
				pexpire(key, milliseconds);
				return str;
			}
		});
	}

	public static String get(final String key) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public static byte[] get(final byte[] key) {
		return execute(new Function<byte[], Jedis>() {

			@Override
			public byte[] callback(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public static Long del(final String key) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public static Long del(final byte[] key) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public static Long hset(final String key, final String field, final String value) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public static Long hset(final String key, final String field, final String value, final Integer seconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				expire(key, seconds);
				return jedis.hset(key, field, value);
			}
		});
	}

	public static Long hset(final String key, final String field, final String value, final Long milliseconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				pexpire(key, milliseconds);
				return jedis.hset(key, field, value);
			}
		});
	}

	public static Long hset(final byte[] key, final byte[] field, final byte[] value) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public static Long hset(final byte[] key, final byte[] field, final byte[] value, final Integer seconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				expire(key, seconds);
				return jedis.hset(key, field, value);
			}
		});
	}

	public static Long hset(final byte[] key, final byte[] field, final byte[] value, final Long milliseconds) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				pexpire(key, milliseconds);
				return jedis.hset(key, field, value);
			}
		});
	}

	public static String hget(final String key, final String field) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public static byte[] hget(final byte[] key, final byte[] field) {
		return execute(new Function<byte[], Jedis>() {

			@Override
			public byte[] callback(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public static Long hdel(final String key, final String... fields) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.hdel(key, fields);
			}
		});
	}

	public static Long hdel(final byte[] key, final byte[]... fields) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.hdel(key, fields);
			}
		});
	}

	public static String hmset(final String key, final Map<String, String> hash) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public static String hmset(final String key, final Map<String, String> hash, final Integer seconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.hmset(key, hash);
				expire(key, seconds);
				return str;
			}
		});
	}

	public static String hmset(final String key, final Map<String, String> hash, final Long milliseconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.hmset(key, hash);
				pexpire(key, milliseconds);
				return str;
			}
		});
	}

	public static String hmset(final byte[] key, final Map<byte[], byte[]> hash) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public static String hmset(final byte[] key, final Map<byte[], byte[]> hash, final Integer seconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.hmset(key, hash);
				expire(key, seconds);
				return str;
			}
		});
	}

	public static String hmset(final byte[] key, final Map<byte[], byte[]> hash, final Long milliseconds) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				String str = jedis.hmset(key, hash);
				pexpire(key, milliseconds);
				return str;
			}
		});
	}

	public static List<String> hmget(final String key, final String... fields) {
		return execute(new Function<List<String>, Jedis>() {

			@Override
			public List<String> callback(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public static List<byte[]> hmget(final byte[] key, final byte[]... fields) {
		return execute(new Function<List<byte[]>, Jedis>() {

			@Override
			public List<byte[]> callback(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public static Long lpush(final String key, final String... strings) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.lpush(key, strings);
			}
		});
	}

	public static Long lpush(final byte[] key, final byte[]... strings) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.lpush(key, strings);
			}
		});
	}

	public static String lpop(final String key) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public static byte[] lpop(final byte[] key) {
		return execute(new Function<byte[], Jedis>() {

			@Override
			public byte[] callback(Jedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	public static Long rpush(final String key, final String... strings) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.rpush(key, strings);
			}
		});
	}

	public static Long rpush(final byte[] key, final byte[]... strings) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.rpush(key, strings);
			}
		});
	}

	public static String rpop(final String key) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public static byte[] rpop(final byte[] key) {
		return execute(new Function<byte[], Jedis>() {

			@Override
			public byte[] callback(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public static List<String> lrange(final String key, final long start, final long end) {
		return execute(new Function<List<String>, Jedis>() {

			@Override
			public List<String> callback(Jedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public static List<byte[]> lrange(final byte[] key, final long start, final long end) {
		return execute(new Function<List<byte[]>, Jedis>() {

			@Override
			public List<byte[]> callback(Jedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public static String rpoplpush(final String srckey, final String dstkey) {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.rpoplpush(srckey, dstkey);
			}
		});
	}

	public static byte[] rpoplpush(final byte[] srckey, final byte[] dstkey) {
		return execute(new Function<byte[], Jedis>() {

			@Override
			public byte[] callback(Jedis jedis) {
				return jedis.rpoplpush(srckey, dstkey);
			}
		});
	}

	public static Set<String> hkeys(final String key) {
		return execute(new Function<Set<String>, Jedis>() {

			@Override
			public Set<String> callback(Jedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public static Set<byte[]> hkeys(final byte[] key) {
		return execute(new Function<Set<byte[]>, Jedis>() {

			@Override
			public Set<byte[]> callback(Jedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public static Map<String, String> hgetAll(final String key) {
		return execute(new Function<Map<String, String>, Jedis>() {

			@Override
			public Map<String, String> callback(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public static Map<byte[], byte[]> hgetAll(final byte[] key) {
		return execute(new Function<Map<byte[], byte[]>, Jedis>() {

			@Override
			public Map<byte[], byte[]> callback(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public static Long llen(final String key) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public static Long llen(final byte[] key) {
		return execute(new Function<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	/**
	 * 说明: 清空所有数据库的所有数据
	 * 
	 * @return
	 */
	public static String flushAll() {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.flushAll();
			}
		});
	}

	/**
	 * 说明: 清空当前所在数据库的数据
	 * 
	 * @return
	 */
	public static String flushDB() {
		return execute(new Function<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.flushDB();
			}
		});
	}

}
