package com.excel.util;

import com.excel.hibernate.common.UserList;

public class Test {
	public static void main(String[] args) {
		try {
			Object obj= null;
			UserList ul = (UserList) obj;
			System.out.println(ul);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
