<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- include 지시자의 역할: 외부의 파일의 내용을 가져와서 삽입시켜줌 --%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>



<div class="card">
   <div class="card-header">
      	상품 목록
   </div>

   <div class="card-body">
		<ul>
			<!-- 
			사실은 getter 메소드를 호출해서 return값을 받아쓰는것
			따라서 el 쓸땐 getter가 필수일 것  -->
			<!-- 컨트롤러에서  cart는 Ch06Cart객체고 세션으로 뭐시기다-->
			<!-- cart 객체에서 꺼낸걸 item이 참조한다 -->
			<c:forEach items="${productList}" var="item">
				<li class="m-2">
					<span>${item.pname}</span>
					<a href="cartadd?pno=${item.pno}&pname=${item.pname}" class="btn btn-info btn-sm">장바구니 넣기</a>
				</li>
			</c:forEach>
			<!-- item이 참조하는 것에 .pname, pno를 담을거다  -->
			
			
		</ul>
   </div>
</div> 



<%-- include 액션의 역할: 외부의 JSP를 실행하고 그 결과를 삽입시켜줌 --%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>         