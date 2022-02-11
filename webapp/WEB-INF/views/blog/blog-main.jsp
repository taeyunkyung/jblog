<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<c:if test="${map.blogVo.logoFile == null}">
						<!-- 기본이미지 -->
						<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:if>
					
					<!-- 사용자업로드 이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${map.blogVo.logoFile}">
					
					<div id="nick">${map.blogVo.userName}(${map.blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<!-- 카테고리 리스트 -->
						<c:forEach items="${map.cateList}" var="cateVo">
							<li><a href="${pageContext.request.contextPath}/${map.blogVo.id}?cateNo=${cateVo.cateNo}">${cateVo.cateName}</a></li>
						</c:forEach>	
						
						<li><a href="$}">미분류</a></li>				
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
					<div id="postTitle" class="text-left"><strong>${readVo.postTitle}</strong></div>
					<div id="postDate" class="text-left"><strong>${readVo.regDate}</strong></div>
					<div id="postNick">${map.blogVo.userName}(${map.blogVo.id})님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post" >
					${readVo.postContent}
				</div>
				
				<!-- //post -->
				
				<!-- 글이 없는 경우 -->
				<c:if test="${readVo == null}">
					<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
						<div id="postDate" class="text-left"><strong></strong></div>
						<div id="postNick"></div>
					</div>
			    
					<div id="post" >
					</div>
				</c:if>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						 
						<c:forEach items="${map.postList}" var="postVo">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath}/${map.blogVo.id}?cateNo=${param.cateNo}&postNo=${postVo.postNo}">${postVo.postTitle}</a></td>
								<td class="text-right">${postVo.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
		
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
</script>
</html>