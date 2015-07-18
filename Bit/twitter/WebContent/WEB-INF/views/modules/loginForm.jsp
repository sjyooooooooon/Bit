<%@page import="com.bit.twitter.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function submitLoginForm() {
		//jQuery ajax 이용 로그인 처리
		var params = {
				login_id:$("#login_id").val(),
				login_pw:$("#login_pw").val()		
				};
		var reqUrl ="<%=request.getContextPath()%>/login.do";
		$.ajax({
			url: reqUrl
			, method: 'post'
			, data: params
			, success: function(result) {
				console.log("로그인 성공!");
				console.log("이름: " + result.name);
				console.log("이메일: " + result.email);
				console.log("닉네임: " + result.nickname);
				
				$("#loginForm").hide();
				$("#memberName").text(result.name);
	            $("#loginMemberInfo").show();
	            
	            getTimeline();
			}
			, error: function (xhr) {
				console.log("로그인 실패!");
				console.log(xhr);
			}
		});
		
		return false;	
	}
	
	$(document).ready(function() {
		//로그아웃 버튼 이벤트 헨들러
		$("#btnLogout").click(function() {
			$("#login_id").val("");
			$("#login_pw").val("");
			// $.get(url, data, callback);
			$.get('<%=request.getContextPath()%>/logout.do'
					, null
					, function(){
						$("#memberName").text("");
			            $("#loginMemberInfo").hide();
						$("#loginForm").show();
				}			
			);
		});
	});
	
	
</script>
<%
Member memberInfo = (Member) session.getAttribute("memberInfo");
%>

<form id="loginForm" onsubmit="return submitLoginForm()" style="<%= memberInfo == null ? "" : "display: none"%>">  <!--  -->
	<label for="login_id">아이디</label>
	<input type="text" name="login_id" id="login_id" />
	<label for="login_pw">비밀번호</label>
	<input type="password" name="login_pw" id="login_pw" />
	<input type="submit" value="로그인" />
	<input type="button" value="회원가입" />
</form>
<div id="loginMemberInfo" style="<%= memberInfo == null ? "display: none" : ""%>">
   <span id="memberName"><%=memberInfo != null ? memberInfo.getName() : "" %></span>님 어서오세요.<!-- for F5 <%--내용 추가--%>  -->
   <button id="btnLogout">로그아웃</button>
</div>