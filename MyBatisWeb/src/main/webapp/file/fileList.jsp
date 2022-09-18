<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"   />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
<h1 style="text-align:center;margin:40px"> 업로드 파일 목록 </h1>

<table class="table table-striped">
	<tr>
		<th>FILE/DIR</th>
		<th>파일명</th>
		<th>파일크기</th>
		<th>마지막수정일</th>
		<th>다운로드</th>
	</tr>
	<!-- ------------------- -->
	<c:forEach var="file" items="${files}">
	<tr>
		<td>
			<c:if test="${file.isFile()}">
				<img src="images/file.png" style="width:30px">
			</c:if>
			<c:if test="${file.isDirectory()}">
				<img src="images/dir.png" style="width:30px">
			</c:if>
		</td>
		<%--${file.getName()}  --%>
		<td>${file.name}</td>
		<td>${file.length()} bytes</td>
		<td>${file.lastModified()}</td>

		
		
		<td>
			<c:if test="${file.isFile() }">
				<button class="btn btn-success"
				 onclick="goDown('${file.name}')">다운로드</button>
			</c:if>
		</td>
	</tr>
	</c:forEach>
	<!-- ------------------- -->
</table>
<!-- 파일 다운로드를 위한 form------------ -->
<form name="df" action="FileDown" method="post">
	<input type="hidden" name="fname">
</form>

<script>
	function goDown(fname){
		//alert(fname);
		df.fname.value=fname;
		df.submit();
	}
</script>

</div>
<jsp:include page="/foot.jsp"   />


