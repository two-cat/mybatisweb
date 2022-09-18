<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<jsp:include page="/carousel.jsp"/>

<div class="container" style="margin-top:2rem">    
	<h1>${title}</h1>
	<h2 class="text-info">c##scott계정의 테이블 수: ${count} </h2>
</div>

<jsp:include page="/foot.jsp"/>