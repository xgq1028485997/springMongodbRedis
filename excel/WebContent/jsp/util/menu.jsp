<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
      	<c:if test="${!empty sessionScope.functionList}">
      		<c:forEach var="fl" items="${sessionScope.functionList}">
      			<li>
					<a id="p_${fl.functionCode}" class="am-cf" data-am-collapse="{target: '#${fl.functionCode }'}" href="javascript:getFunction('${fl.functionCode}')" data-url="${fl.functionUrl }">
						<span class="${fl.functionIcon} am-icon-fw"></span> ${fl.functionName}
						<span id="s_${fl.functionCode }" class="am-icon-angle-down am-fr am-margin-right"></span>
					</a>
					<ul class="am-list am-collapse admin-sidebar-sub" id="${fl.functionCode}"></ul>
				</li>
      		</c:forEach>
      	</c:if>
      </ul>

      <%-- <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 用户信息</p>
          <a href="${pageContext.request.contextPath}/system/notice/noReadList"><p class="notice-latest"></p></a>
          <p>用户编号：${sessionScope.loginInfo.getUserNo()}</p>
          <p>用&nbsp;&nbsp;户&nbsp;&nbsp;名：${sessionScope.loginInfo.getUserName()}</p>
        </div>
      </div> --%>

    
    </div>
  </div>
  <script type="text/javascript">
    	
    	$(document).ready(function(){   
    		$("#admin-offcanvas").css({"height":getWindowSub()});
    		$(".admin-offcanvas-bar").css({"height":getWindowSub()});
    	});
    	
    	function getFunction(functionCode){
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
        							html += "<li><a href='javascript:void(0)' onclick='loadPage(\""+fl[i].functionUrl+"\",null,null)'  class='am-cf menuBtn'> <span class='"+fl[i].functionIcon+" am-icon-fw'></span> "+fl[i].functionName+"</a></li>";
            					}
        						$('#'+functionCode).html(html);
        					}else{
        						var dataUrl = $('#p_'+functionCode).attr("data-url");
        						loadPage(dataUrl,null,null);
        					}
        		});
    		}
    	}
    </script>