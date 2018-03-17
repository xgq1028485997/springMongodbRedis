package com.excel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelConstant {
	public static final String USERNO = "userNo";
	public static final String USERNAME = "userName";
	public static final String LOGININFO = "loginInfo";
	public static final String FUNCTIONLIST = "functionList";
	public static final String LOGINURL = "/login.jsp";
	public static final String ERRORMESSAGE = "errorMessage";
	
	public static final boolean TRUE = true;
	public static final boolean FALSE = true;
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final Date getNowDate(){
		try {
			return SDF.parse(SDF.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
