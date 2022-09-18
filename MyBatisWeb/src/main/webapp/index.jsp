<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- index.jsp -->
<jsp:include page="/top.jsp" />
<jsp:include page="/carousel.jsp" />

<div class="section">
	<div class="container">
		<h1>Main페이지</h1>

		<h2 class="text-danger">${title}</h2>
	</div>
</div>

<div class="section">
	<div class="container">
		<div class="row" style="padding:1rem">
			<div class="col-xs-3">
				<h3>전남 영광시</h3>
			</div>
			<div class="col-xs-2 col-xs-offset-7" style="padding-top:1rem">
				<button>리뷰 확인</button>
			</div>
		</div>
	</div>

</div>
<jsp:include page="/foot.jsp" />
