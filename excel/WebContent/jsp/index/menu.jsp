<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 一级菜单  --> 
<div class="layui-collapse" lay-accordion="" style="width: 200px;height:652px;">
	<c:if test="${!empty sessionScope.functionList}">
		<c:forEach var="fl" items="${sessionScope.functionList}">
			<c:if test="${fl.functionPcode==fl.functionCode}">
				<c:choose>
					<c:when test="${!empty fl.functionUrl}">
						<div class="layui-colla-item" onclick="loadPage('${fl.functionUrl}',null,null)">
							<div class="my-layui-colla-title">
								<a href="javascript:;">${fl.functionName} </a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="layui-colla-item">
							<div class="layui-colla-title">
								<a href="javascript:;">${fl.functionName} </a>
							</div>
							<!-- 二级菜单  -->
							<div class="layui-colla-content">
								<div class="layui-collapse" lay-accordion="">
									<c:if test="${!empty sessionScope.functionList}">
										<c:forEach var="flc" items="${sessionScope.functionList}">
											<c:if test="${flc.functionPcode==fl.functionCode && flc.functionPcode!=flc.functionCode}">
												<c:choose>
													<c:when test="${!empty flc.functionUrl}">
														<div class="layui-colla-item" onclick="loadPage('${flc.functionUrl}',null,null)">
															<div class="my-layui-colla-title">
																<a href="javascript:;">${flc.functionName} </a>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<div class="layui-colla-item" onclick="getFunction('${flc.functionCode}','${flc.functionUrl}')">
															<div class="layui-colla-title">
																<a href="javascript:;">${flc.functionName}</a>
															</div>
															<!-- 三级菜单 -->
															<div class="layui-colla-content" id="${flc.functionCode}"></div>
														</div>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
	</c:if>
</div>




<%-- <div class="layui-side layui-bg-black">
	<div class="layui-side-scroll">
		<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		<ul class="layui-nav layui-nav-tree">
			<c:if test="${!empty sessionScope.functionList}">
				<c:forEach var="fl" items="${sessionScope.functionList}">
					<li class="layui-nav-item">
						<a href="javascript:getFunction('${fl.functionCode}','${fl.functionUrl}')">${fl.functionName} </a> 
						<c:if test="${empty fl.functionUrl}">
							<ul class="layui-nav-child" id="${fl.functionCode}"></ul>
						</c:if>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</div> --%>
<script type="text/javascript">
	$(document).ready(function(){
		
	});



	function getFunction(functionCode,functionUrl){
		if(isNotNull(functionUrl)){
			loadPage(functionUrl,null,null);
		}else{
			var oldHtml = $('#'+functionCode).html();
    		//若已加载子目录，则只用data-am-collapse控制显示，否则加载
    		if(oldHtml==""||oldHtml==null){
    			$.post("${pageContext.request.contextPath}/index/getFunctionList",
        				{functionCode : functionCode},
        				function callback(data){
        					var fl = data.fl_map;
        					if(fl!=null&&fl.length>0){
        						var html = '';
        						for(var i=0;i<fl.length;i++){
        							html += "<div class='layui-collapse' lay-accordion=''>"; 
        							if(!isNotNull(fl.functionUrl)){
        								html += "<h2 class='layui-colla-title' onclick='loadPage(\""+fl[i].functionUrl+"\",null,null)'>";
       									html += "<a href='javascript:;'>"+fl[i].functionName+" </a>";
       									html += "</h2>";
        							}else{
        								html += "<div class='layui-colla-item' onclick='getFunction(\""+fl[i].functionCode+"\",\""+fl[i].functionUrl+"\")'>";
        								html += "<h2 class='layui-colla-title'><a href='javascript:;'>"+fl[i].functionName+" </a></h2>"
        								html += "<div class='layui-colla-content' id='"+fl[i].functionCode+"'></div>";
        								html += "</div>";
        							}
        							html += "</div>";
            					}
        						$('#'+functionCode).html(html);
        					}
        				}
        		);
    		}
		}
	}
</script>