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
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<div id="header" class="clearfix">
			<h1><a href="${pageContext.request.contextPath}/${blogVo.id}">${blogVo.blogTitle}</a></h1>
			<ul class="clearfix">
				<li><a class="btn_s" href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">내블로그 관리</a></li>
				<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</ul>						
		</div>
		
		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	$(document).ready(function() {
		var url = document.location.href;
		var split = [];
		split = url.split("/");
		var id = split[4];
		
		fetchList(id);
	});
	
	$("#btnAddCate").on("click", function() {
		var cateName = $("[name='name']").val();
		var description = $("[name='desc']").val();
		
		var cateVo = {
			cateName: cateName,
			description: description
		};
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/cate/add",		
			type : "post",
			// contentType : "application/json",
			data : cateVo,

			dataType : "json",
			success : function(cateVo){
				render(cateVo, 'up');
				
				$("[name='name']").val("");
				$("[name='desc']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		}); 
	});
	
	function fetchList(id) {
		$.ajax({			
			url : "${pageContext.request.contextPath}/cate/list",		
			type : "post",
			// contentType : "application/json",
			data : {id: id},

			dataType : "json",
			success : function(cateList){
				console.log(cateList);	
				
				for(var i = 0; i < cateList.length; i++) {
					render(cateList[i], 'down');
				}				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		}); 
	};
	
	function render(cateVo, updown) {
		var str = '';
		str += '<tr id="t'+ cateVo.cateNo +'">';
		str += '	<td>'+ cateVo.cateNo +'</td>';
		str += '	<td>'+ cateVo.cateName +'</td>';
		str += '	<td>'+ cateVo.count +'</td>';
		str += '	<td>'+ cateVo.description +'</td>';
		str += '	<td class="text-center">';
		str += '		<img class="btnCateDel" onclick="delCate('+ cateVo.cateNo +', '+ cateVo.count +')" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
		str += '</tr>';
		
		if(updown == 'up') {
			$("#cateList").prepend(str);
		} else {
			$("#cateList").append(str);
		}
	};
	
	function delCate(cateNo, count) {
		console.log(count);
		var cateNo = cateNo;
		
		if(count > 0) {
			alert("삭제할 수 없습니다.");
			return false;
		}
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/cate/delete",		
			type : "post",
			// contentType : "application/json",
			data : {cateNo: cateNo},

			dataType : "json",
			success : function(result){
				if(result == 'success') {
					$("#t"+cateNo).remove();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		});
	};
</script>

</html>