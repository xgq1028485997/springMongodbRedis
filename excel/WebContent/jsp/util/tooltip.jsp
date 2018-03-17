<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<small class="tooltips" <c:if test="${requestScope.errorMessage== null || requestScope.errorMessge==''}">style="display:none;"</c:if> >${requestScope.errorMessage }</small>
