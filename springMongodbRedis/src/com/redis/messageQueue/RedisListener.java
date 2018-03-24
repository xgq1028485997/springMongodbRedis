/**   
* Copyright © 2018 公司名. All rights reserved.
* 
* @Title: RedisListener.java 
* @Prject: springMongodbRedis
* @Package: com.redis.messageQueue 
* @Description: TODO
* @author: 肖光清   
* @date: 2018年3月24日 下午3:28:49 
* @version: V1.0   
*/
package com.redis.messageQueue;

import java.io.Serializable;

/** 
* @ClassName: RedisListener 
* @Description: TODO
* @author: 肖光清
* @date: 2018年3月24日 下午3:28:49  
*/
public class RedisListener {
	
	public void handleMessage(Serializable message){
		System.out.println(message);
	}
	
}
