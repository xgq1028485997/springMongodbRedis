package org.base;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.excel.util.ExcelConstant;

public class SysHandlerInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(SysHandlerInterceptor.class);
	// 后台session控制  
    private String[] noFilters = new String[] { }; 
    
	public String[] getNoFilters() {
		return noFilters;
	}
	public void setNoFilters(String[] noFilters) {
		this.noFilters = noFilters;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//就简单判断了�?下，如果要详细控制可以用spring security
		String requestUrl = request.getRequestURI();
		//System.out.println("requestUrl:"+requestUrl);
		logger.info("requestUrl:"+requestUrl);
		for(String url : noFilters) {  
            if(requestUrl.endsWith(url)) {  
                return true;  
            }  
        }  
		 
		if(request.getSession() != null && request.getSession().getAttribute(ExcelConstant.LOGININFO) != null) 
			return true;
			
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		response.addHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Expires","0");
		String errors = "您还没有登录，或者session已过期。请先登陆!";
        request.setAttribute("Message", errors);
        //跳转至登录页面
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
        if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
        	//ajax请求
        	response.setHeader("sessionstatus","timeout");
        	response.addHeader("loginPath", basePath+"login.jsp");// 返回url

        }
		out.println("<script>alert('用户会话己失效，请重新登Excel!');window.parent.location=\""+basePath+"login.jsp\";</script>");
		out.flush();
		out.close();
		return false;
	}
	
}
