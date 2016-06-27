<%-- 
    Document   : index
    Created on : Jun 27, 2016, 2:44:35 AM
    Author     : haonguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/GetCapabilities">GetCapabilities</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/DescribeRecord">GetRecord</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/GetRecordByID/1">GetRecord with file id = 1</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/GetRecordByID/2">GetRecord with file id = 2</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/GetRecord/GEOTIFF">GetRecord with format GEOTIFF</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/GetRecord/MOD021KM">GetRecord with format MOD021KM</a>
        <br>
        <a href="http://localhost:8080/MavenWebProject/VNSC/csw/download/LC81230522014071LGN00_MTL.txt">Download  with file id = LC81230522014071LGN00_MTL.txt</a>
        <h4>But the fist, you need change link in ReadXML, FileDownloadImpl(in your computer ) to test </h4>
    </body>
</html>
