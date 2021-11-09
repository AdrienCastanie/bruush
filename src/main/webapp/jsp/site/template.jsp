<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TD3</title>
    	<link rel="stylesheet" type="text/css" href="css/body.css"/>
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	</head>
	<body>
    	<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>
		<section class="corps">
			<jsp:include page="${content}.jsp"/>	
		</section>
	</body>
</html>