<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<jsp:include page="/top.jsp" />
<div class="container" style="margin-top:2rem;">
	<h1 style="text-align:center">Board View</h1>
	
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-hover table-striped">
				<tr>
					<td style="width:20%"><b>글번호</b></td>
					<td style="width:30%">${board.idx}</td>
					<td style="width:20%">작성일</td>
					<td style="width:30%">${board.wdate}</td>
				</tr>
				
				<tr>
					<td style="width:20%"><b>글쓴이</b></td>
					<td style="width:30%">${board.name}</td>
					<td style="width:20%">조회수</td>
					<td style="width:30%">${board.readnum}</td>
				</tr>
				
				<tr>
					<td style="width:20%"><b>첨부파일</b></td>
					<td colspan="3">
					
					<a href="#" onclick="goDown('${board.filename}')">${board.filename}</a>
					 
					[${board.filesize} bytes]</td>
				</tr>
				<tr>
					<td style="width:20%"><b>제목</b></td>
					<td colspan="3">${board.subject}</td>
				</tr>
				<tr style="height:200px;">
					<td style="width:20%"><b>글내용</b></td>
					<td colspan="3" style="overflow: auto">
					<pre style="border:none">${board.content}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-center">
					<a href="list.ict"><i class="fa fa-2x fa-align-justify fa-fw"></i></a>|
					<c:if test="${loginUser ne null}">
						<a href="#" onclick="goEdit()"><i class="fa fa-edit fa-fw fa-2x"></i></a>|
						<a href="#" onclick="goDel()"><i class="fa fa-2x fa-cut fa-fw"></i></a>|
					</c:if>					
					<a href="javascript:goRe()"><i class="fa fa-2x fa-fw fa-reply hub"></i></a>
					</td>
				</tr>
				
			</table>
		</div> <!-- .col end -->
	</div> <!-- .row end -->
	<!-- 댓글 쓰기 form시작 --------------------------- -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<jsp:include page="/board/reviewForm.jsp" />
		</div>
	</div>
	<!-- ----------------------------------- -->
	<!--  댓글 목록 가져오기 ---------------------->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<jsp:include page="/board/reviewList.jsp" />
		</div>
	</div>
</div> <!-- .container end -->
<!-- 게시글 삭제 또는 수정 폼 ------------------- -->
<form name="frm" method="post">
	<input type="hidden" name="idx" value="${board.idx}">
</form>
<!-- ----------------------------------- -->
<script>
	function goDel(){
		let yn = confirm('정말 삭제할까요?');
		if(!yn) return;
		
		frm.action="${pageContext.request.contextPath}/user/board/delete.ict";
		frm.submit();
	}
	function goEdit(){
		frm.action="${pageContext.request.contextPath}/user/board/edit.ict";
		frm.submit();
	}
	function goDown(fname){
		location.href='../FileDown?fname='+encodeURIComponent(fname);
		//encodeURIComponent(): uri에 들어오는 한글을 인코딩 처리해줌
	}

</script>

<jsp:include page="/foot.jsp" />


