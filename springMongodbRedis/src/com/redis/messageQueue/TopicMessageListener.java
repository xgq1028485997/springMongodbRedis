/**   
* Copyright © 2018 公司名. All rights reserved.
* 
* @Title: TopicMessageListener.java 
* @Prject: springMongodbRedis
* @Package: com.redis.messageQueue 
* @Description: TODO
* @author: 肖光清   
* @date: 2018年3月24日 下午10:21:57 
* @version: V1.0   
*/
package com.redis.messageQueue;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: TopicMessageListener
 * @Description: TODO
 * @author: 肖光清
 * @date: 2018年3月24日 下午10:21:57
 */
public class TopicMessageListener implements MessageListener {
	
	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*
	 * 消息接收(subscribe):
	 * @Title: onMessage
	 * @Description: TODO
	 * @param message
	 * @param arg1
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(Message message, byte[] arg1) {
		byte[] body = message.getBody();// 请使用valueSerializer
		byte[] channel = message.getChannel();
		// 请参考配置文件，本例中key，value的序列化方式均为string。
		// 其中key必须为stringSerializer。和redisTemplate.convertAndSend对应
		String itemValue = (String) redisTemplate.getValueSerializer().deserialize(body);
		String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);
		System.out.println(itemValue + "            "  + topic);
	}

}
