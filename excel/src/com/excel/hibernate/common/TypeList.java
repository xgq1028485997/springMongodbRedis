package com.excel.hibernate.common;

import java.io.Serializable;
import java.util.Date;

public class TypeList implements Serializable{

	/**
	* @fieldName: serialVersionUID
	* @fieldType: long
	* @Description: TODO
	*/
	private static final long serialVersionUID = 1L;
	private Integer typeId;
	private String typeNo;
	private String typeName;
	private Date typeAddtime;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getTypeAddtime() {
		return typeAddtime;
	}
	public void setTypeAddtime(Date typeAddtime) {
		this.typeAddtime = new java.util.Date(typeAddtime.getTime());
	}
}
