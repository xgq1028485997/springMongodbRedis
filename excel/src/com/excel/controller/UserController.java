package com.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.LayuiPage;

import com.excel.hibernate.common.SerialNumberList;
import com.excel.hibernate.common.UserList;
import com.excel.service.UserService;
import com.excel.util.ExcelConstant;

import net.sf.json.JSONObject;
/**
 * 
* @ClassName: UserController 
* @Description: 用户管理模块
* @author: 肖光清
* @date: 2017年8月18日 下午4:51:43
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 检索用户信息
	* @Title: getUserList 
	* @Description: TODO
	* @param model
	* @param page
	* @param request
	* @param response
	* @return
	* @return: ModelAndView
	 */
	@RequestMapping(value="/getUserList")
	@ResponseBody
	public String getUserList(Model model,LayuiPage lp,HttpServletRequest request,HttpServletResponse response){
		String userNo = request.getParameter("userNo");
		String userName = request.getParameter("userName");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		JSONObject jo = new JSONObject();
		StringBuffer sb = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		if(StringUtils.isNotBlank(userNo)){
			sb.append(" and user_no like ? ");
			obj.add("%" + userNo + "%");
		}if(StringUtils.isNotBlank(userNo)){
			sb.append(" and user_name like ? ");
			obj.add("%" + userName + "%");
		}if(StringUtils.isNotBlank(page)&&StringUtils.isNotBlank(limit)){
			lp.setPage(Integer.parseInt(page));
			lp.setLimit(Integer.parseInt(limit));
		}else{
			lp.setPage(1);
			lp.setLimit(10);
		}
		try {
			List<Object> list = userService.getUserList(sb, obj, lp);
			jo.put("code", 0);
			jo.put("msg", "");
			jo.put("count", lp.getTotalSize());
			jo.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	/**
	 * 
	* @Title: addUser 
	* @Description: 添加或修改用户
	* @param request
	* @param response
	* @return
	* @return: Map<String,Object>
	 */
	@RequestMapping(value = "/addOrEditUser",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addOrEditUser(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userNo = request.getParameter("userNo");
		String userPassword = request.getParameter("userPassword");
		String userSex = request.getParameter("userSex");
		String userBirth = request.getParameter("userBirth");
		String userProvince = request.getParameter("userProvince");
		String userCity = request.getParameter("userCity");
		String userCounty = request.getParameter("userCounty");
		String userAddr = request.getParameter("userAddr");
		String userTel = request.getParameter("userTel");
		String userCard = request.getParameter("userCard");
		String userRemark = request.getParameter("userRemark");
		
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag = true;
		UserList user = new UserList();
		StringBuffer sb = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		try {
			if(StringUtils.isNotBlank(userId)){
				user = (UserList) userService.getObjById(UserList.class, Integer.parseInt(userId));
				if(user==null){
					flag = false;
					map.put("flag", false);
					map.put("info", "数据已不存在");
				}
			}else{
				/*sb.append(" and user_no = ?");
				obj.add(userNo);
				List<Object> list =  userService.getUserList(sb, obj,null);
				if(list!=null&&list.size()>0){
					flag = false;
					map.put("flag", false);
					map.put("info", "用户编号已存在");
				}*/
				user.setUserName(userName);//姓名
				SerialNumberList snl = userService.addSerialNumber();
				user.setUserNo(snl.getSerialNumberNo());//编号
			}
			if(flag){
				
				user.setUserPassword(userPassword);
				user.setUserAddtime(ExcelConstant.getNowDate());
				user.setUserSex(userSex);//性别
				user.setUserProvince(userProvince);//省
				user.setUserCity(userCity);//市
				user.setUserCounty(userCounty);//县
				user.setUserAddr(userAddr);//详细地址
				user.setUserTel(userTel);//联系方式
				user.setUserCard(userCard);//证件
				user.setUserVisible("1");
				user.setUserRemark(userRemark);
				user.setUserBirth(ExcelConstant.YMD.parse(userBirth));
				
				userService.addOrEditUser(user);
				
				if(StringUtils.isNotBlank(userId)){
					map.put("flag", true);
					map.put("info", "修改成功");
				}else{
					map.put("flag", true);
					map.put("info", "添加成功");
				}
			}
		} catch (Exception e) {
			map.put("flag", false);
			map.put("info", "添加失败");
			e.printStackTrace();
		}
		return map;
	}
	 /**
	  * 删除用户
	 * @Title: delUser 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @return: Map<String,Object>
	  */
	@RequestMapping(value = "/delUser",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delUser(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserList> ul = new ArrayList<>();
		try {
			if(StringUtils.isNotBlank(userId)){
				String[] ui = userId.split(",");
				for(String u : ui){
					UserList user = (UserList) userService.getObjById(UserList.class,Integer.parseInt(u));
					ul.add(user);
				}
				
			}
			userService.delUser(ul);
			map.put("flag", true);
			map.put("info", "删除成功");
		} catch (Exception e) {
			map.put("flag", false);
			map.put("info", "删除失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="getUserObj",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUserObj(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(StringUtils.isNotBlank(userId)){
				UserList user = (UserList) userService.getObjById(UserList.class, Integer.parseInt(userId));
				if(user!=null){
					map.put("obj", user);
					map.put("info", "数据获取成功");
					map.put("flag", true);
				}else{
					map.put("info", "数据已不存在");
					map.put("flag", false);
				}
				
			}
		} catch (Exception e) {
			map.put("info", "数据获取失败");
			map.put("flag", false);
			e.printStackTrace();
		}
		return map;
	}
}
