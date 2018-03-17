package com.excel.sql;

public class FunctionListSql {
	public static final String getFunctionList(){
		StringBuffer sb = new StringBuffer("select function_id functionId,function_pcode functionPcode,function_code functionCode,");
		sb.append("function_name functionName,function_url functionUrl,function_sort functionSort,function_remark functionRemark,");
		sb.append("function_delete functionDelete,STR_TO_DATE(DATE_FORMAT(function_addtime,'%y-%m-%d'),'%y-%m-%d') functionAddtime,");
		sb.append("function_icon functionIcon,function_type functionType from function_list where 1=1 ");
		return sb.toString();
	}
}
