package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisTest {

	@Test
	public void testJedisCluster() throws Exception{
		
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("47.107.119.174", 7001));
		nodes.add(new HostAndPort("47.107.119.174", 7002));
		nodes.add(new HostAndPort("47.107.119.174", 7003));
		nodes.add(new HostAndPort("47.107.119.174", 7004));
		nodes.add(new HostAndPort("47.107.119.174", 7005));
		nodes.add(new HostAndPort("47.107.119.174", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("test", "123");
		String string = jedisCluster.get("test");
		System.out.println(string);
	}
}
