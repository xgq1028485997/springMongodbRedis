package com.excel.sql;

public class UserListSql {
	
	public static final String getUserList(){
		StringBuffer getUserList = new StringBuffer(" select user_id userId,user_no userNo,user_name userName,user_birth userBirth, ");
		getUserList.append(" user_password userPassword,user_sex userSex,user_province userProvince,user_city userCity,");
		getUserList.append(" user_county userCounty,user_addr userAddr,user_tel userTel,user_card userCard,user_remark userRemark,");
		getUserList.append(" STR_TO_DATE(DATE_FORMAT(user_addtime, '%y-%m-%d'), '%y-%m-%d') userAddtime from user_list where 1=1 ");
		return getUserList.toString();
	}
	
	
}
