package com.mongodb.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.collection.UserList;
import com.mongodb.service.UserService;
import com.mongodb.util.BaseController;
import com.mongodb.util.UtilBase;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/getUserList")
	public void getUserList(){
		PrintWriter pw = null;
		JSONObject jo = new JSONObject();
		Map<String,Object> params = new HashMap<String,Object>();
		String collectionName = "user_list";
		try {
			params.put("userId", "1");
			List<UserList> list = userService.getUserList(params,collectionName);
			jo.put("list", list);
			pw = getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.print(jo.toString());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/addUser")
	public void addUserList(){
		PrintWriter pw = null;
		JSONObject jo = new JSONObject();
		UserList userList = new UserList();
		try {
			userList.setUserId("1");
			userList.setUserName("肖光清");
			userList.setUserPassword("1");
			userList.setUserNo("xgq");
			userList.setAddtime(UtilBase.h_ymd.format(new Date()));
			userService.addUserList(userList,"user_list");
			jo.put("success", true);
			pw = getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.print(jo.toString());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/updateUser")
	public void updateUserList(){
		PrintWriter pw = null;
		JSONObject jo = new JSONObject();
		Map<String,Object> params = new HashMap<String,Object>();
		String collectionName = "user_list";
		try {
			params.put("userId", "1");
			params.put("userName", "肖光清1");
			params.put("userPassword", "1");
			params.put("userNo", "xgq");
			params.put("addtime", UtilBase.h_ymd.format(new Date()));
			userService.updateUserList(params,collectionName);
			jo.put("success", true);
			pw = getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.print(jo.toString());
		pw.flush();
		pw.close();
	}
}
