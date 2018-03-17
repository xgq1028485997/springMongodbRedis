package org.util;

public class PageUtil implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageSize = 10; // 每页显示条数
	private int currentPage = 1;// 当前页
	private int totalPage;// 总共页数
	private int totalSize;// 总记录数
	private int showPageNum = 5;// 显示出来的页码数量
	private int start = 1;
	private int end = 1;

	public PageUtil() {
		super();
	}

	public PageUtil(int pageSize, int currentPage) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public PageUtil(int pageSize, int currentPage, int totalPage,
			int totalSize) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.totalSize = totalSize;
	}

	/**
	 * 每页第一条记录码
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		this.totalPage = this.totalSize % this.pageSize == 0 ? this.totalSize
				/ this.pageSize : (this.totalSize / this.pageSize + 1);
		if (this.totalPage == 0) {
			this.totalPage = 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		if (getCurrentPage() - showPageNum / 2 > 1) {
			if (getCurrentPage() + showPageNum / 2 <= getTotalPage()) {
				start = getCurrentPage() - showPageNum / 2;
			} else {
				start = getTotalPage() - showPageNum + 1;
			}
		} else {
			start = 1;
		}
		if ((getTotalPage() - start) < showPageNum) {
			start = getTotalPage() - showPageNum + 1;
		}
		if (start <= 0) {
			start = 1;
		}
		return this.start;
	}

	public int getEnd() {
		if (showPageNum < getTotalPage()) {
			end = start + showPageNum;
		}
		if ((getTotalPage() - start) < showPageNum) {
			end = totalPage + 1;
		}
		if (end > getTotalPage()) {
			end = getTotalPage() + 1;
		}
		return this.end;
	}
	
	
	
	/**
	 * @Description: 获取分页字符串
	 * @author: yang.ma
	 * @date: 2015-5-15 下午3:47:14
	 */
	public String pageString(PageUtil page) {
		StringBuffer buffer = new StringBuffer("<ul class='am-pagination'>");
		if(page.getCurrentPage()==1){
			buffer.append("<li class='am-disabled'><a class='page_s' href='javascript:void(0);'>首页</a></li>");
			buffer.append("<li class='am-disabled'><a class='page_s' href='javascript:void(0);'>«</a></li>");
		}else{
			buffer.append("<li><a class='page_s' href='javascript:void(0);' lang='1'>首页</a></li>");
			buffer.append("<li><a class='page_s' href='javascript:void(0);' lang='").append(page.getCurrentPage()-1).append("'>«</a></li>");
		}
		
		for(int i = page.getStart(); i < page.getEnd() ; i++){
			if(i == page.getCurrentPage()){
				buffer.append("<li class='am-active'><a class='page_s' href='javascript:void(0);' lang='").append(i).append("'>").append(i).append("</a></li>");
			}else{
				buffer.append("<li><a class='page_s' href='javascript:void(0);' lang='").append(i).append("'>").append(i).append("</a></li>");
			}
		}
		
		if(page.getCurrentPage() == page.getTotalPage()){
			buffer.append("<li class='am-disabled'><a class='page_s' href='javascript:void(0);'>»</a></li>");
			buffer.append("<li class='am-disabled'><a class='page_s' href='javascript:void(0);'>尾页</a></li> ");
		}else{
			buffer.append("<li><a class='page_s' href='javascript:void(0);' lang='").append(page.getCurrentPage()+1).append("'>»</a></li>");
			buffer.append("<li><a class='page_s' href='javascript:void(0);' lang='").append(page.getTotalPage()).append("'>尾页</a></li> ");
		}
		
		buffer.append("</ul>");
		return buffer.toString();
	}
}
