package com.excel.hibernate.common;

import java.io.Serializable;
import java.util.Date;

public class SerialNumberList implements Serializable{

	/**
	* @fieldName: serialVersionUID
	* @fieldType: long
	* @Description: TODO
	*/
	private static final long serialVersionUID = 1L;
	
	private Integer serialNumberId;
	private String serialNumberNo;
	private String serialNumberType;
	private String serialNumberPrefix;
	private String serialNumberNum;
	private String serialNumberSuffix;
	private String serialNumberConnect;
	private String serialNumberRemark;
	private Date serialNumberAddtime;
	public String getSerialNumberNo() {
		return serialNumberNo;
	}
	public void setSerialNumberNo(String serialNumberNo) {
		this.serialNumberNo = serialNumberNo;
	}
	public String getSerialNumberPrefix() {
		return serialNumberPrefix;
	}
	public void setSerialNumberPrefix(String serialNumberPrefix) {
		this.serialNumberPrefix = serialNumberPrefix;
	}
	public String getSerialNumberNum() {
		return serialNumberNum;
	}
	public void setSerialNumberNum(String serialNumberNum) {
		this.serialNumberNum = serialNumberNum;
	}
	public String getSerialNumberSuffix() {
		return serialNumberSuffix;
	}
	public void setSerialNumberSuffix(String serialNumberSuffix) {
		this.serialNumberSuffix = serialNumberSuffix;
	}
	public String getSerialNumberConnect() {
		return serialNumberConnect;
	}
	public void setSerialNumberConnect(String serialNumberConnect) {
		this.serialNumberConnect = serialNumberConnect;
	}
	public String getSerialNumberRemark() {
		return serialNumberRemark;
	}
	public void setSerialNumberRemark(String serialNumberRemark) {
		this.serialNumberRemark = serialNumberRemark;
	}
	public Date getSerialNumberAddtime() {
		return serialNumberAddtime;
	}
	public void setSerialNumberAddtime(Date serialNumberAddtime) {
		this.serialNumberAddtime = new java.util.Date(serialNumberAddtime.getTime());
	}
	public Integer getSerialNumberId() {
		return serialNumberId;
	}
	public void setSerialNumberId(Integer serialNumberId) {
		this.serialNumberId = serialNumberId;
	}
	public String getSerialNumberType() {
		return serialNumberType;
	}
	public void setSerialNumberType(String serialNumberType) {
		this.serialNumberType = serialNumberType;
	}
	
}
