<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />

<div class="container">
	<h1>파일 업로드 결과2</h1>
	<h2>업로드 파일명:${filename}</h2>
	<h2>원본 파일명: ${origin}</h2>
	<h2>파일 크기: ${fsize} bytes</h2>
	<hr>
	<h1><a href="fileList.ict">파일 목록 보러 가기</a></h1>
</div>
<jsp:include page="/foot.jsp" />