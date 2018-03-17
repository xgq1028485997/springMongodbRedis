package com.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.LayuiPage;

import com.excel.hibernate.common.TypeList;
import com.excel.service.TypeService;
import com.excel.util.ExcelConstant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/type")
public class TypeController{
	@Resource(name="typeService")
	private TypeService typeService;
	
	@ResponseBody
	@RequestMapping(value="getTypeList")
	public String getTypeList(Model model,LayuiPage lp,HttpServletRequest request,HttpServletResponse response){
		String typeNo = request.getParameter("typeNo");
		String typeName = request.getParameter("typeName");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		JSONObject jo = new JSONObject();
		StringBuffer sb = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		if(StringUtils.isNotBlank(typeNo)){
			sb.append(" and type_no like ? ");
			obj.add("%" + typeNo + "%");
		}if(StringUtils.isNotBlank(typeName)){
			sb.append(" and type_name like ? ");
			obj.add("%" + typeName + "%");
		}if(StringUtils.isNotBlank(page)&&StringUtils.isNotBlank(limit)){
			lp.setPage(Integer.parseInt(page));
			lp.setLimit(Integer.parseInt(limit));
		}else{
			lp.setPage(1);
			lp.setLimit(10);
		}
		try {
			List<Object> list = typeService.getTypeList(sb, obj, lp);
			jo.put("code", 0);
			jo.put("msg", "");
			jo.put("count", lp.getTotalSize());
			jo.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="addOrEditType")
	public Map<String,Object> addOrEditType(HttpServletRequest request,HttpServletResponse response){
		String typeNo = request.getParameter("typeNo");
		String typeName = request.getParameter("typeName");
		String typeId = request.getParameter("typeId");
		Map<String,Object> map = new HashMap<String, Object>();
		TypeList tl = new TypeList();
		boolean flag = true;
		StringBuffer sb = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		try {
			if(StringUtils.isNotBlank(typeId)){
				tl = (TypeList) typeService.getObjById(TypeList.class,Integer.parseInt(typeId));
				if(tl == null){
					flag = false;
					map.put("flag", false);
					map.put("info", "类型已不存在，无法修改！");
				}
			}else{
				sb = new StringBuffer(" and type_no = ? ");
				obj.add(typeNo);
				List<Object> tlo = typeService.getTypeList(sb, obj, null);
				if(tlo!=null&&tlo.size()>0){
					flag = false;
					map.put("flag", false);
					map.put("info", "类型编码已存在，无法提交！");
				}
				tl.setTypeAddtime(ExcelConstant.getNowDate());
			}
			if(flag){
				tl.setTypeNo(typeNo);
				tl.setTypeName(typeName);
				
				typeService.addOrEditType(tl);
				
				map.put("flag", true);
				map.put("info", "提交成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", false);
			map.put("info", "提交失败");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="delType")
	public Map<String,Object> delType(HttpServletRequest request,HttpServletResponse response){
		String typeId = request.getParameter("typeId");
		Map<String,Object>  map = new HashMap<String,Object>();
		List<TypeList> tl = new ArrayList<TypeList>();
		try {
			if(StringUtils.isNotBlank(typeId)){
				String ti[] = typeId.split(",");
				for(String id : ti){
					TypeList t = (TypeList) typeService.getObjById(TypeList.class, Integer.parseInt(id));
					tl.add(t);
				}
				
				typeService.delType(tl);
				
				map.put("flag", true);
				map.put("info", "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", false);
			map.put("info", "删除失败");
		}
		
		return map;
	}
	
}
