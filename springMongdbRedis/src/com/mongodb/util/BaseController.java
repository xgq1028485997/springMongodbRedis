package com.mongodb.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	this.request = request;
	this.response = response;
	this.session = request.getSession();
	}

	/*
	 * 
	 */
	public PrintWriter getWriter() throws IOException {  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
        return response.getWriter();  
    }  
}
