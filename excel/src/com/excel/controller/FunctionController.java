package com.excel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.LayuiPage;

import com.excel.service.FunctionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Resource(name="functionService")
	private FunctionService functionService;
	
	@ResponseBody
	@RequestMapping(value = "/getFunctionList")
	public String getFunctionList(Model model,LayuiPage lp,HttpServletRequest request,HttpServletResponse response){
		String functionCode = request.getParameter("functionCode");
		String functionName = request.getParameter("functionName");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		
		JSONObject jo = new JSONObject();
		StringBuffer sb = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(functionCode)){
			sb.append(" and fl.function_Code like ");
			obj.add(functionCode+"%");
		}if(StringUtils.isNotBlank(functionName)){
			sb.append(" and fl.functionName like ");
			obj.add("%" + functionCode + "%");
		}if(StringUtils.isNotBlank(page)&&StringUtils.isNotBlank(limit)){
			lp.setPage(Integer.parseInt(page));
			lp.setLimit(Integer.parseInt(limit));
		}else{
			lp.setPage(1);
			lp.setLimit(10);
		}
		try {
			List<Object> fl = functionService.getFunctionList(sb, obj, lp);
			jo.put("code", 0);
			jo.put("msg", "");
			jo.put("count", lp.getTotalSize());
			jo.put("data", fl);
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jo.toString();
	}
}
