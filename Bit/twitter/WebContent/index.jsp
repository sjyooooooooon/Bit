<%@page import="java.math.BigDecimal"%>
<%@page import="com.bit.twitter.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Member memberInfo = (Member) session.getAttribute("memberInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트위터</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script>
	$(document).ready(function() {
		getTimeline();
		
		$("#btnTweet").click(function() {
			$("#newTweet").show();
		});
		
		$("#btnCancelNewTweet").click(function() {
			$("#newTweetContent").val("");
			$("#newTweet").hide();
		});
		
		$("#btnFollow").click(doFollow); 
	});
	
	function doFollow(){
		$.ajax({
			url: "<%=request.getContextPath()%>/follow.do", 
			method: "post", 
			data: {content:$("#newTweetContent").val()}, 
			success: function(result) {
				console.log("새 트윗 등록 성공 여부: " + result);
				if (result) {
					$("#newTweetContent").val("");
					$("#newTweet").hide();
					getTimeline();
				} else {
					alert("트윗 등록 실패. 잠시후 다시 시도하거나 관리자에게 문의!");
				}
			}, error: function(xhr) { // 에러가 나면 request객체가 리턴됨
				console.log(xhr);
			}, statusCode: {
				400: function() {
					alert("내용을 입력해야 함");
					$("#newTweetContent").focus();
				},
				401: function() { //로그인 안된 경우
					alert("로그인 하세요");
				},
				500: function() {}
			}
		});
	};
	
	function getTimeline() {
		// 전체 타임라인 조회
 		// var params ={member_seq: <%= memberInfo != null ? memberInfo.getMemberSeq() : null%>}; 
		$.get("<%=request.getContextPath()%>/timeline.do",
				null, 
				function(list) {
					console.log(list);
					updateTwit(list);
		});
	}
	
	function getUserTimeline(memberSeq){
		$.get("<%=request.getContextPath()%>/timeline.do",
				{memberSeq:memberSeq},
				function(list){
					console.log('getUserTimeline() success, list=');
					console.log(list);
					$("#top").hide();
					$("#userTop").show();
					updateTwit(list);
					$("#userName").text(list[0].name);
					$("#userEmail").text("( "+list[0].email+" )");
					$("#btnFollow").text(list[0].followStatus);
					//TODO : 팔로우인지 언팔인지 조회!
				});
	}
	
	function updateTwit(list){
		//기존 타임라인을 비우는 코드 
		$("#twits").html("");
		
		$(list).each(function(index,element){
			// "twits"에 자식 노드로 넣기
			console.log('updateTwit()----------');
			console.log('memberSeq=' + element.memberSeq);
			var html = '<li>';
			<!-- html += '<div class="twit-nickname"><a href="<%=request.getContextPath()%>/timeline.do?memberSeq='+element.member_seq+'">'+element.nickname+'</a></div>';-->
			html += '<div class="bold"><span onclick="getUserTimeline('+element.memberSeq+')">'+element.nickname+'</span></div>';
			html += '<div class="twit_content">'+element.content+'</div>';
			html += '<div class="bg-gray">'+element.regDtm+'</div>';
			html += '</li>';
			$("#twits").append(html);
		})
	}
	
	function submitNewTweet() {
		$.ajax({
			url: "<%=request.getContextPath()%>/write.do", 
			method: "post", 
			data: {content:$("#newTweetContent").val()}, 
			success: function(result) {
				console.log("새 트윗 등록 성공 여부: " + result);
				if (result) {
					$("#newTweetContent").val("");
					$("#newTweet").hide();
					getTimeline();
				} else {
					alert("트윗 등록 실패. 잠시후 다시 시도하거나 관리자에게 문의!");
				}
			}, error: function(xhr) { // 에러가 나면 request객체가 리턴됨
				console.log(xhr);
			}, statusCode: {
				400: function() {
					alert("내용을 입력해야 함");
					$("#newTweetContent").focus();
				},
				401: function() { //로그인 안된 경우
					alert("로그인 하세요");
				},
				500: function() {}
			}
		});
		
		return false;    // 폼이 전송되지 않도록
	}

</script>
</head>
<body>

	<div id="top">
		<h2><a href="<%=request.getContextPath()%>/" style="text-decoration: none;">트위터</a></h2>
		<jsp:include page="/WEB-INF/views/modules/loginForm.jsp"/>
		<button id="btnTwit">트윗하기</button>
	</div>
	
	<div id="userTop" style="display: none;">
		<h2><a href="<%=request.getContextPath()%>/" style="text-decoration: none;">트위터</a></h2>
		<span id="userName"></span>
		<span id="userEmail"></span>
		<button id="btnFollow">FOLLOW</button>
	</div>
	<div id="timeline" >
		<ul id="twits">
			<!-- <li><span>트윗번호</span><span>글내용</span><span>시간</span></li> -->
		</ul>
	</div>
	<div id="newTweet">
		<form onsubmit="return submitNewTweet()">
			<textarea id="newTweetContent" style="width:395px; height:40px;"></textarea>
			<button id="btnSubmitNewTweet" type="submit">트윗</button>
			<button id="btnCancelNewTweet" type="reset">취소</button>
		</form>
	</div>

</body>
</html>