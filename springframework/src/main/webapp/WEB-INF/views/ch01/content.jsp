<!-- 태그 주석 (응답에 포함) -->
<%-- JSP 주석 (응답에 포함 안됨) --%>

<%-- page 지시자 --%>
<%-- 
language : 프로그래밍 언어의 종류
pageEncoding : JSP 소스를 작성할 때 사용할 문자셋 (다국어 이용 => UTF-8)
contentType : JSP 실행 결과(응답 내용)의 종류 (MIME타입; charset=응답을 구성하는 문자셋)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

 <div class="card">
   <div class="card-header">
      ch01/content
   </div>

   <div class="card-body">
	교재(PPT)를 보고 따라서 해봅시다.
   </div>
</div> 

<%@ include file="/WEB-INF/views/common/bottom.jsp" %>
