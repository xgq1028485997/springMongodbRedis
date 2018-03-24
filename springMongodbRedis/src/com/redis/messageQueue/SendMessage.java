/**   
* Copyright © 2018 公司名. All rights reserved.
* 
* @Title: SendMessage.java 
* @Prject: springMongodbRedis
* @Package: com.redis.messageQueue 
* @Description: TODO
* @author: 肖光清   
* @date: 2018年3月24日 下午7:20:46 
* @version: V1.0   
*/
package com.redis.messageQueue;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/** 
* @ClassName: SendMessage 
* @Description: TODO
* @author: 肖光清
* @date: 2018年3月24日 下午7:20:46  
*/
@Component
public class SendMessage {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	/**
	 * 发送消息
	* @Title: sendMessage 
	* @Description: TODO
	* @param channel
	* @param message
	* @return: void
	 */
	public void sendMessage(String channel,Serializable message){
		redisTemplate.convertAndSend(channel, message);
	}
}
