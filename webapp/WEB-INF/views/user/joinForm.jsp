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

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button" value="false">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2">사용가능한 아이디입니다.</td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit">회원가입</button>
				</div>	      		
			</form>
			
		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>

<script type="text/javascript">
	$(document).ready(function () {
		$("#tdMsg").hide();
	});	

	$("#btnJoin").on("click", function() {	
		
		if(!$("#txtId").val()) {
			$("#btnJoin").attr("disabled", true);
			alert("아이디를 입력해주세요.");	
			$("#btnJoin").attr("disabled", false);
			return false;
			
		} else if ($("#btnIdCheck").prop("value") == 'false') {
			$("#btnJoin").attr("disabled", true);
			alert("아이디 중복체크를 해주세요.");
			$("#btnJoin").attr("disabled", false);
			return false;
			
		} else if (!$("#txtPassword").val()) {
			$("#btnJoin").attr("disabled", true);
			alert("비밀번호를 입력해주세요.");	
			$("#btnJoin").attr("disabled", false);
			return false;
			
		} else if (!$("#txtUserName").val()) {
			$("#btnJoin").attr("disabled", true);
			alert("이름을 입력해주세요.");	
			$("#btnJoin").attr("disabled", false);
			return false;	
			
		} else if ($("#chkAgree").is(":checked") == false) {
			$("#btnJoin").attr("disabled", true);
			alert("약관에 동의해주세요.");
			$("#btnJoin").attr("disabled", false);
			return false;			
		}		
	});

	$("#btnIdCheck").on("click", function() {
		$("#btnIdCheck").attr("value", true);
		var keyword = $("#txtId").val();
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/user/checkId",		
			type : "post",
			//contentType : "application/json",
			data : {keyword: keyword},

			dataType : "json",
			success : function(result){
				if(result == true) {
					$("#tdMsg").show();
				} else {
					alert("다른 아이디로 가입해주세요.");
				}														
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		});
	});
	
</script>

</html>