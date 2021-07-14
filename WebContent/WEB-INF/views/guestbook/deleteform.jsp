<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 
<%
	String no = request.getParameter("no");

	System.out.println(no);
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

	<form action="/mysite/gbc?action=delete" method="get" >
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="submit" value="확인"></td>
				<td><input type="hidden" name="action" value="delete"> </td>
			</tr>
		</table>
		<a href="/mysite/gbc?action=list">메인으로 돌아가기</a>
		<br>
		<input type='hidden' name="no" value="<%=no%>">
		
	</form>
	
	
</body>
</html>