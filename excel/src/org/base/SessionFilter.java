package org.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excel.util.ExcelConstant;

public class SessionFilter implements Filter{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
	    HttpServletResponse response = (HttpServletResponse) arg1;
	    HttpSession session = request.getSession();
		//判断session是否过期
        if (session==null||session.getAttribute(ExcelConstant.LOGININFO) == null) {
            String errors = "用户会话己失效，请重新登陆2!";
            request.setAttribute("Message", errors);
            //跳转至登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            arg2.doFilter(request, response);
        }

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
