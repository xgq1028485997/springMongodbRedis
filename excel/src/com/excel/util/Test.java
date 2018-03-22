package com.excel.util;

import com.excel.hibernate.common.UserList;

public class Test {
	public Test(){
		System.out.println(this.getClass().getResource("/"));
	}
	public static void main(String[] args) {
		try {
			Test t = new Test();
			Object obj= null;
			UserList ul = (UserList) obj;
			System.out.println(ul);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
