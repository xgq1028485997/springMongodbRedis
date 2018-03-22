package com.mongodb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {
	public Test(){
		try {
			System.out.println(URLDecoder.decode(this.getClass().getClassLoader().getClass().getResource("/cache-config.properties").getPath().substring(1),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Test();
	}
}
