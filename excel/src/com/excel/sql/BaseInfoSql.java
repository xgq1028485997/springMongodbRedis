package com.excel.sql;

public class BaseInfoSql {
	
	public static final String getExcelFucntion = "select fl.function_id functionId,fl.function_pcode functionPcode,"
			+ "fl.function_code functionCode,fl.function_url functionUrl,fl.function_sort functionSort,"
			+ "fl.function_remark functionRemark,fl.function_delete functionDelete,"
			+ "STR_TO_DATE(DATE_FORMAT(fl.function_addtime, '%y-%m-%d'), '%y-%m-%d') functionAddtime,"
			+ "fl.function_name functionName,fl.function_icon functionIcon from function_list fl "
			+ "where fl.function_type='Excel_1' and function_delete='0' ";
	
}
