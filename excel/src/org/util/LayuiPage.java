package org.util;

public class LayuiPage implements java.io.Serializable{
	/**
	* @fieldName: serialVersionUID
	* @fieldType: long
	* @Description: TODO
	*/
	private static final long serialVersionUID = 1L;
	private int page;			//第几页
	private int limit;			//每页几条数据
	private int totalSize;		//总条数
	
	public LayuiPage() {
		super();
	}

	public LayuiPage(int page, int limit) {
		this.page = page;
		this.limit = limit;
	}
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		return (getPage()-1)*getLimit();
	}
	
	public int getEnd() {
		return getLimit();
	}
	
	
	
}
