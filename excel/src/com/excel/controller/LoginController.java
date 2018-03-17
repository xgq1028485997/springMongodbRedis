package com.excel.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.base.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.excel.hibernate.common.UserList;
import com.excel.service.LoginService;
import com.excel.util.ExcelConstant;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("/lc")
public class LoginController extends BaseController{
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Resource(name="loginService")
	private LoginService loginService;
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	//@AvoidDuplicateSubmission(save=true)//避免重复提交Annotation 提交页面或者表单进入时增加 save=true，表单提交后增加 remove=true 
	public Map<String,Object> login(HttpServletRequest request,HttpServletResponse response){
		logger.info("-------login-------");
		String userNo = request.getParameter("userNo");
		String userPassword = request.getParameter("userPassword");
		List<Object> parms = new ArrayList<Object>();
		/*JSONObject jo = new JSONObject();
		PrintWriter pw = null;*/
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuffer where = new StringBuffer();
		try {
			sessionOut(request,response);
			if(StringUtils.isNotBlank(userNo)){
				where.append(" and user_no = ? ");
				parms.add(userNo.trim());
				if(StringUtils.isNotBlank(userPassword)){
					where.append(" and user_password = ? ");
					parms.add(userPassword.trim());
					//获取用户信息
					List<Object> userList = loginService.getUserList(where,parms);
					if(userList!=null&&userList.size()>0){
						UserList user = (UserList) userList.get(0);
						HttpSession session = request.getSession();
						session.setAttribute(ExcelConstant.LOGININFO, user);
						map.put("flag", true);
						map.put("url", "/index/baseInfo");
						//forward 前缀来达到转发到其它资源的目的
						//return new ModelAndView("forward:/index/baseInfo");
					}else{
						map.put("info", "用户编号或密码不正确");
					}
				}else{
					map.put("info", "密码为空");
				}
			}else{
				map.put("info", "用户编号为空");
			}
			
			//pw = response.getWriter();
		} catch (Exception e) {
			map.put("info", "登陆错误");
			e.printStackTrace();
		}finally {
			/*pw.print(jo.toString());
			pw.flush();
			pw.close();*/
		}
		return map;
		//return new ModelAndView("forward:/lc/toLogin").addObject(ExcelConstant.ERRORMESSAGE,info);
	}
	
	public void sessionOut(HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="loginOut",method=RequestMethod.POST)
	public Map<String,Object> loginOut(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", ExcelConstant.LOGINURL);
		try {
			HttpSession session = request.getSession();
			if(session != null){
				sessionOut(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="toLogin",method=RequestMethod.POST)
	public void toLogin(HttpServletRequest request,HttpServletResponse response){
		String errorMessage = request.getParameter(ExcelConstant.ERRORMESSAGE);
		JSONObject jo = new JSONObject();
		PrintWriter pw = null;
		try {
			jo.put("info", errorMessage);
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pw.print(jo.toString());
			pw.flush();
			pw.close();
		}
		
	}
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView index(){
		logger.info("--------Refresh-----------");
		if(session==null||session.getAttribute(ExcelConstant.LOGININFO)==null){
			
		}
		return new ModelAndView("redirect:/index.jsp");
	}
}
