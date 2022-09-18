<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-hover table-striped">
	<tr class="info">
		<th style="text-align:center"><h3>:: Reply List::</h3></th>
	</tr>
	<c:forEach var="reply" items="${replyArr}">
	<tr>
		<td>
			${reply.re_content}<br>
			<span class="pull-right">${reply.re_name} [${reply.re_date}]</span>
			<br>
			<c:if test="${loginUser.userid eq reply.re_name}">
				<div class="pull-right">
					<button class="btn btn-info" 
					onclick="reEdit('${reply.re_idx}')">수정</button>
					<button class="btn btn-danger"
					 onclick="reDel('${reply.re_idx}')">삭제</button>
				</div>
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
<!--댓글 수정 또는 삭제 form --------------------- -->
<form name="reDelF">
	<input type="hidden" name="re_idx">
	<input type="hidden" name="idx_fk" value="${board.idx}">
</form>
<!-- -------------------------------------- -->
<script>
	function reEdit(num){
		//alert(num);
		reDelF.re_idx.value=num;
		reDelF.action="../user/board/replyEdit.ict";
		reDelF.method="post";
		reDelF.submit();		
	}//------------------

	function reDel(num){
		//alert(num);
		let yn = confirm("댓글을 정말 삭제할까요?");
		if(!yn) return;
		
		reDelF.re_idx.value = num;
		reDelF.action="../user/board/replyDel.ict";
		reDelF.method="post";
		reDelF.submit();
	}
</script>


