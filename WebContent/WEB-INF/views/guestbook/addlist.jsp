<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>

<%
	
	List<GuestbookVo> guestbookList = (List<GuestbookVo>)request.getAttribute("gList");
	
	System.out.println("jsp========================");
	System.out.println(guestbookList.toString());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>

	<form action="/mysite/gbc?action=add" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button> <input type="hidden" name="action" value="add"> <br></td>
			</tr>
		</table>
	</form>
	<br/>

	<% 
		for(GuestbookVo guestbookVo :guestbookList){
	%>
			<table border="1"  width="500px">
				<tr>
					<td>[<%=guestbookVo.getNo() %>]</td>
					<td><%=guestbookVo.getName() %></td>
					<td><%=guestbookVo.getRegDate() %></td>
					<td><a href="/mysite/gbc?action=dform&no=<%=guestbookVo.getNo()%>">삭제</a></td>
										
				</tr>
				<tr>
					<td colspan=4><%=guestbookVo.getContent() %></td>
				</tr>
			</table>
		    <br/>
	<% 
		}
	%>
</body>
</html>