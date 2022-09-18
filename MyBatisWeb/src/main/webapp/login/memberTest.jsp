<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 소스를 가져오는 방식으로 로그인모듈을 포함시키자. -->
   
<jsp:include page="/top.jsp" />
<div id="content">
	<h1>회원 인증 페이지</h1>
	<h2 style="color:blue">회원들만 들어올 수 있는 페이지 입니다.</h2>
	
	<h2 style="color:red">
 			${loginUser.name} [${loginUser.userid}]님 환영합니다.
	</h2>
</div>	
<jsp:include page="/foot.jsp" />