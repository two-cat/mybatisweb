<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/layout_flex.css">
</head>
<body>
	<c:if test="${mode=='get'}">
	<div id="content" style="height:390px">
		<form name="idf" action="idCheck.do" method="post">
			<label for="userid">아이디</label>
			<input type="text" name="userid" id="userid"
			 placeholder="User ID" autofocus="autofocus"
			 class="box">
			<button class="btn">확인</button>
		</form>
	</div>
	</c:if>
	<c:if test="${mode =='post'}">
			<c:if test="${result =='ok'}">
			<div id="content" style="height:390px">
				<h3>${userid}를 사용할 수 있습니다.</h3>
				<button class="btn" onclick="setId('${userid}')">닫  기</button>
			</div>
			</c:if>
			<c:if test="${result=='fail'}">
			<div id="content" style="height:390px">
				<h3>${userid}는 이미 사용 중입니다.</h3>
				<form name="idf" action="idCheck.do" method="post">
				<label for="userid">아이디</label>
				<input type="text" name="userid" id="userid"
				 placeholder="User ID" autofocus="autofocus"
				 class="box">
				<button class="btn">확  인</button>
				</form>
			</div>
			</c:if>
	</c:if>			
		<script>
			function setId(uid){
				//alert(uid);
				//uid값을 부모창(join.jsp)의 userid의 value값으로 전달한다.
				//부모창 [opener==> window객체]
				//window > document > form
				opener.document.mf.userid.value = uid;
				
				//팝업창 닫기 [팝업창=> self]
				self.close();
				
			}
		</script>
</body>
</html>