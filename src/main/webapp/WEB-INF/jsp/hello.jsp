<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
</head>
<body>
    <h1>Hello ${name}, Please Enter Metadata ID (1 - 2)</h1>
    <form action="get-data/" commandName="search" method="POST">
        <input name="searchid"/>
        <input type="submit" value="Load"/>
    </form>
</body>
</html>