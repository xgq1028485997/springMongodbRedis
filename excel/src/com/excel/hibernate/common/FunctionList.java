package com.excel.hibernate.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FunctionList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer functionId;
	private String functionPcode;
	private String functionCode;
	private String functionName;
	private String functionUrl;
	private BigDecimal functionSort;
	private String functionRemark;
	private String functionDelete;
	private Date functionAddtime;
	private String functionIcon;
	private String functionType;
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	public String getFunctionPcode() {
		return functionPcode;
	}
	public void setFunctionPcode(String functionPcode) {
		this.functionPcode = functionPcode;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	public BigDecimal getFunctionSort() {
		return functionSort;
	}
	public void setFunctionSort(BigDecimal functionSort) {
		this.functionSort = functionSort;
	}
	public String getFunctionRemark() {
		return functionRemark;
	}
	public void setFunctionRemark(String functionRemark) {
		this.functionRemark = functionRemark;
	}
	public String getFunctionDelete() {
		return functionDelete;
	}
	public void setFunctionDelete(String functionDelete) {
		this.functionDelete = functionDelete;
	}
	public Date getFunctionAddtime() {
		return functionAddtime;
	}
	public void setFunctionAddtime(Date functionAddtime) {
		this.functionAddtime = new java.util.Date(functionAddtime.getTime());
	}
	public String getFunctionIcon() {
		return functionIcon;
	}
	public void setFunctionIcon(String functionIcon) {
		this.functionIcon = functionIcon;
	}
	public String getFunctionType() {
		return functionType;
	}
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	
}
