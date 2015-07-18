<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Object twitList = (Object) request.getAttribute("d");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타임라인</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/js/timeline.js"></script>

<script>
console.log("in timeline.jsp");
<%-- updateTimeline(<%=twitList%>); --%>
</script>
</head>
<body>
<div id="timeline">
	<button id="btnTweet">트윗하기</button>
	<ul id="twits">
		<!-- <li><span>트윗 번호</span><span>트윗 내용</span><span>시간</span></li> -->
	</ul>
</div>
</body>
</html>