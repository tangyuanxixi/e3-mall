package cn.e3mall.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

public class JedisClientTest {

	@Test
	public void testJedisClient() throws Exception{
		//初始化spring 容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("usr", "123");
		String string = jedisClient.get("usr");
		System.out.println(string);
		//从容器中取得jedisClient对象
		
	}
}
