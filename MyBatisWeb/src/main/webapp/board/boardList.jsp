<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/top.jsp" />

<div class="container">

	<h1 style="text-align:center">Board List</h1>
	<p style="text-align:center">
		<a href="../user/board/form.ict">글쓰기</a>
	</p>
	<!-- 검색 폼 시작-------------------------------- -->
	<form name="frm" action="list.ict">
	<div class="row">
		<div class="col-md-2 col-md-offset-1">
			<select name="findType" class="form-control">
				<option value="0">::검색 유형::</option>
				<option value="1">제목</option>
				<option value="2">작성자</option>
				<option value="3">글내용</option>
			</select>
		</div>
		<div class="col-md-6">
			<input type="search" name="findKeyword" required
			 placeholder="검색어" class="form-control">
		</div>
		<div class="col-md-3">
			<button class="btn btn-success">검  색</button>
		</div>		
	</div>
	</form>
	<!-- --------------------------------------- -->
	
	<table class="table table-striped" style="margin-top:2rem;">
		<tr>
			<th>글번호</th>
			<th style="width:40%">제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<!-- ------------------------ -->
		<c:if test="${boardArr eq null or empty boardArr}">
			<tr>
				<td colspan="5"><b>게시글이 없습니다</b></td>
			</tr>
		</c:if>
		<c:if test="${boardArr ne null and not empty boardArr}">
		<c:forEach var="board" items="${boardArr}">
		<tr>
			<td>${board.idx}</td>
			<td>
			<!-- 글제목 -->
			<a href="view.ict?idx=${board.idx}">${board.subject}</a>
			
			<!-- 첨부파일 여부 -->
			<c:if test="${board.filename != null}">			
				<img src="../images/file.png" style="width:22px">
			</c:if>
			
			<!-- 댓글수 보여주기 -->
			<c:if test="${board.re_cnt > 0}">
				<span class="badge">${board.re_cnt}</span>
			</c:if>
			
			</td>
			<td>${board.name}</td>
			<td>${board.wdate}</td>
			<td>${board.readnum}</td>
		</tr>
		</c:forEach>
		</c:if>
		<!-- ------------------------ -->
		<tr>
			<td colspan="3" style="text-align:center">
			<!-- page navigation  
			begin:시작값 지정
			end : 끝값
			step: 증가치
			-->
			<ul class="pagination">
			<c:forEach var="i" begin="1" end="${pageCount}" step="1">
				<%-- <a href="list.ict?cpage=${i}">[${i}]</a> --%>
				<li <c:if test="${cpage eq i}">class='active'</c:if> >
					<a href="list.ict?cpage=${i}&findType=${param.findType}&findKeyword=${param.findKeyword}">${i}</a>
				</li>
			</c:forEach>
			</ul>
			
			</td>		
			<td colspan="2" style="text-align:right">
				<span class="text-primary">
					총 게시글 수 : ${totalCount} 개
				</span>
			</td>		
		</tr>
	</table>
	
</div>



<jsp:include page="/foot.jsp" />