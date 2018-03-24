package com.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Object2Byte {
	/**
	 * Object转bytes[] -- 序列化 ： 将对象转化成一个字节序列并保存起来，随时可恢复成原来的对象（相当于“一个对象不好处理，则把对象拆分成字节来处理，要用时，随时可恢复成对象）
	* @Title: object2Bytes 
	* @Description: TODO
	* @param obj
	* @return
	* @throws Exception
	* @return: byte[]
	 */
	public static byte[] object2Bytes(Object obj) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		byte[] bytes = baos.toByteArray();
		baos.close();
		oos.close();
		return bytes;
		
	}
	/**
	 * byte[]转Object  -- 反序列化
	* @Title: bytes2Object 
	* @Description: TODO
	* @param bytes
	* @return
	* @throws Exception
	* @return: Object
	 */
	public static Object bytes2Object(byte[] bytes)throws Exception{
		ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(baos);
		return ois.readObject();
	}
	
}
