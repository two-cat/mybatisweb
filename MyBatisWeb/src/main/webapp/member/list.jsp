<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/top.jsp" />

<div id="content">
   <h1>User List [Admin Page]</h1>
   
   <div class="searchFrm">
   	<!--검색 폼--------------------  -->
   	<form name="findF" action="find.jsp" method="get">
   		<table class="table" style="width:70%">
   			<tr>
   				<td style="width:15%">
   					<select name="findType">
   						<option value="">::검색 유형::</option>
   						<option value="1">회원이름</option>
   						<option value="2">아이디</option>
   						<option value="3">연락처</option>
   					</select>
   				</td>
   				<td>
   					<input type="search" name="findKeyword" required
   					 placeholder="검색어를 입력하세요" class="box lg">
   					 <button class="btn">검   색</button>
   				</td>   				
   			</tr>	
   		</table>
   	</form>
   </div>
   
   <table class="table" border="1">
      <tr>
         <th>번호</th>
         <th>이름</th>
         <th>아이디</th>
         <th>연락처</th>
         <th>가입일</th>
         <th>수정|삭제</th> 
      </tr>
      <!-- ----------------------- -->
      <c:if test="${memberList eq null or empty memberList }">
      
      <!-- eq연산자 (equals, == 와 동일한 연산자)
      	   ne연산자 (not equals , != 동일한 연산자)
      	   or연산자 (||와 동일)
      	   and연산자(&&와 동일)  
      -->
      	<tr>
      		<td colspan="6">데이터가 없습니다.</td>
      	</tr>
      
      </c:if>
      <c:if test="${memberList ne null and not empty memberList}">
      		<c:forEach var="user" items="${memberList}">
            <tr>
               <td>${user.idx}</td>
               <%-- ${user.getIdx()} --%>
               <td>${user.name}</td>
               <td>${user.userid}</td>
               <td>${user.allHp}
               <%-- ${user.getAllHp()} --%>
               </td>
               <td>${user.indate}</td>
               <td>
               <a href="javascript:goEdit('${user.idx}')">수정</a>|
               <a href="#" onclick="goDel('${user.idx}')">삭제</a>
               </td>
            </tr>
            </c:forEach>
     </c:if>
      <!-- ----------------------- -->
   </table>
   <!--  삭제 또는 수정 form ---------- -->
   <form name="f">
   		<input type="hidden" name="idx">
   </form>
   <!-- ---------------------------- -->
</div>
<script>
	function goEdit(num){
		f.idx.value = num;
		f.action="update.do";
		f.method="post";
		f.submit();
	}//-------------------
	function goDel(num){
		let yn = confirm(num+"번 회원정보를 정말 삭제할까요?");
		if(yn){
			f.idx.value=num;
			f.action="delete.do";
			f.method="post";
			f.submit();
		}
	}//----------------
</script>
<jsp:include page="/foot.jsp" />


