<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />

<div class="container">
	<h1>파일 업로드 결과</h1>
	<h2>content type: ${ctype}</h2>
	<h2>content length: ${len}</h2>
	<hr>
	<pre>
	${requestData}
	</pre>
</div>
<jsp:include page="/foot.jsp" />