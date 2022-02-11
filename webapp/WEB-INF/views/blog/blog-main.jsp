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

<style type="text/css">
#comment-t {margin: 20px;}
#cmtInput {width: 500px; height: 30px;}
#cmtBtn {width: 40px; height: 30px;}
</style>

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
					<input type="hidden" name="postNo" value="${readVo.postNo}">
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
				
				<div id="comments">
					<table id="comment-t">
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:if test="${!empty authUser}">
						<thead>						
							<tr>
								<td>${map.blogVo.userName}</td>
								<td colspan="2"><input id="cmtInput" type="text" name="cmtContent">
									<input type="hidden" name="postNo" value="${readVo.postNo}"></td>	
								<td><button id="cmtBtn" type="submit">저장</button></td>
							</tr>
						</thead>
						</c:if>
						
						<tbody id="cmtList">
							<!-- 코멘트 리스트 -->
						</tbody>					
					</table>
				</div>
				
				
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
					
					<div id="paging">
						<ul>
							<c:if test="${map.prev == true}">
								<li><a href="${pageContext.request.contextPath}/${map.blogVo.id}?crtPage=${map.spgBtnNo - 1}">◀</a></li>
							</c:if>
							
							<c:forEach begin="${map.spgBtnNo}" end="${map.epgBtnNo}" step="1" var="page">
								<c:choose>
									<c:when test="${param.crtPage == page}">
										<li class="active"><a href="${pageContext.request.contextPath}/${map.blogVo.id}?crtPage=${page}">${page}</a></li>								
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/${map.blogVo.id}?crtPage=${page}">${page}</a></li>
									</c:otherwise>	
								</c:choose>							
							</c:forEach>
							
							<c:if test="${map.next == true}">
								<li><a href="${pageContext.request.contextPath}/${map.blogVo.id}?crtPage=${map.epgBtnNo + 1}">▶</a></li>
							</c:if>							
						</ul>
					</div>
					
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
	$(document).ready(function() {
		console.log("리스트출럭");
		var postNo = $("[name='postNo']").val();
		
		fetchList(postNo);
	});

	$("#cmtBtn").on("click", function() {
		console.log("코멘트버튼");
		var postNo = $("[name='postNo']").val();
		var cmtContent = $("[name='cmtContent']").val();
				
		var commentVo = {
			postNo: postNo,
			cmtContent: cmtContent
		};
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/cmt/add",		
			type : "post",
			// contentType : "application/json",
			data : commentVo,

			dataType : "json",
			success : function(commentVo){
				render(commentVo, 'up');
				
				$("[name='cmtContent']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		}); 
	});
	
	function fetchList(postNo) {
		$.ajax({			
			url : "${pageContext.request.contextPath}/cmt/list",		
			type : "post",
			// contentType : "application/json",
			data : {postNo: postNo},

			dataType : "json",
			success : function(cmtList){
				console.log(cmtList);	
				
				for(var i = 0; i < cmtList.length; i++) {
					render(cmtList[i], 'down');
				}				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		}); 
	};
	
	function render(commentVo, updown) {
		var str = '';
		str += '<tr id="t'+ commentVo.cmtNo +'">';
		str += '	<td>'+ commentVo.userName +'</td>';
		str += '	<td class="text-right">'+ commentVo.cmtContent +'</td>';
		str += '	<td class="text-right">'+ commentVo.regDate +'</td>';
		str += '	';
		str += '		<td class="text-center">';
		str += '			<img class="btnCmtDel" onclick="delCmt('+ commentVo.cmtNo +', '+ commentVo.userNo +')" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
		str += '	';
		str += '</tr>';
		
		if(updown == 'down') {
			$("#cmtList").append(str);
		} else {
			$("#cmtList").prepend(str);
		};
	};
	
	function delCmt(cmtNo, userNo) {
		console.log("코멘트삭제");
		var delInfoVo = {
			cmtNo: cmtNo,
			userNo: userNo
		}
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/cmt/delete",		
			type : "post",
			// contentType : "application/json",
			data : delInfoVo,

			dataType : "json",
			success : function(result){
				if(result == 'success') {
					$("#t"+cmtNo).remove();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		});
	};

</script>
</html>