package com.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excel.service.IndexService;
import com.excel.util.ExcelConstant;

@Controller
@Scope("prototype")
@RequestMapping("/index")
@SessionAttributes(ExcelConstant.FUNCTIONLIST)
public class IndexController {
	private static final Logger log = Logger.getLogger(IndexController.class);
	@Resource(name="indexService")
	private IndexService indexService;
	/**
	 * 基础信息
	 * @return
	 */
	@RequestMapping(value="/baseInfo",method=RequestMethod.GET)
	public String baseInfo(Model model,HttpServletRequest request,HttpServletResponse response){
		log.info("------baseInfo-------");
		try {
			Map<String,List<Object>> fl_map = getFunctionList(request,response);
			if(fl_map!=null){
				List<Object> fl = (List<Object>)fl_map.get("fl_map");
				model.addAttribute(ExcelConstant.FUNCTIONLIST, fl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//redirect 前缀来达到重定向到其它资源的目的
		return "redirect:/index.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/getFunctionList")
	public Map<String,List<Object>> getFunctionList(HttpServletRequest request,HttpServletResponse response){
		log.info("------------getFunctionList-----------");
		String functionPcode = request.getParameter("functionCode");
		List<Object> parms = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		Map<String,List<Object>> map = new HashMap<String,List<Object>>();
		if(StringUtils.isNotBlank(functionPcode)){
			where.append(" and fl.function_pcode = ? and fl.function_pcode<>fl.function_code");
			parms.add(functionPcode);
		}
		try {
			List<Object> fl = indexService.getFunctionList(where,parms);
			map.put("fl_map", fl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/*@RequestMapping(value="/goIndex",method=RequestMethod.GET)
	public ModelAndView goIndex(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView();
	}*/
}
