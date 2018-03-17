package com.excel.sql;

public class TypeListSql {
	public static final String getTypeList(){
		StringBuffer sb = new StringBuffer("select type_id typeId,type_no typeNo,type_name typeName,type_addtime typeAddtime ");
		sb.append(" from type_list where 1=1 ");
		return sb.toString();
	}
}
