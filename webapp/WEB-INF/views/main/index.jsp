<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="center-content">
		
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		<form id="search-form" method="post" action="${pageContext.request.contextPath}/search">
			<fieldset>
				<input type="text" name="keyword" >
				<button id="btnSearch" type="submit">검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle"> 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName"> 
			</fieldset>
		</form>
		
		<div id="resultList">
			<table>
				<colgroup>
					<col style="width: 50px;">
					<col style="width: 200px;">
					<col style="width: 100px;">
				</colgroup>	
				<thead>
					<tr>
						<td>사진</td>
						<td>블로그 제목</td>
						<td>이름(아이디)</td>
						<td>가입일</td>
					</tr>
				</thead>					
				<!-- 검색결과 -->	
				<tbody>
					<c:forEach items="${searchList}" var="blogVo">
						<tr>
							<td><img src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}"></td>
							<td>${blogVo.blogTitle}</td>
							<td>${blogVo.userName}(${blogVo.id})</td>
							<td>${blogVo.joinDate}</td>
						</tr>
					</c:forEach>
				</tbody>	
			</table>
		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>
	<!-- //center-content -->
</body>

<script type="text/javascript">
</script>

</html>