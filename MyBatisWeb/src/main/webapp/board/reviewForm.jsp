<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<form name="reF" action="../user/board/replyEnd.ict" method="post">
	<!-- hidden field (작성자, 부모글번호) -------------- -->
	<input type="hidden" name="re_name" value="${loginUser.userid}">
	<input type="hidden" name="idx_fk" value="${board.idx}">
	<!-- -------------------------------------------- -->
	<table class="table">
		<tr>
			<td style="text-align: center"><img src="../images/avatar.png"
				class="img img-thumbnail" style="width: 80px"> <br> <span
				class="label label-warning">${loginUser.userid}</span></td>
			<td style="width: 70%"><textarea name="re_content" rows="4"
					placeholder="로그인해야 이용 가능해요"
					<c:if test="${loginUser eq null}">disabled</c:if>
					class="form-control"></textarea></td>
			<td>
				<button class="btn btn-info">댓글쓰기</button>
			</td>
		</tr>
	</table>
</form>