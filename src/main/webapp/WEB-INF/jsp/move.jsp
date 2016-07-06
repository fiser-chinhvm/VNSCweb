<%-- 
    Document   : move
    Created on : Jul 2, 2016, 5:47:28 PM
    Author     : chinhvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <body ng-app="App" ng-controller="Ctrl">
        <table class = "table">
    <thead>
        <tr>
            <th>Field</th>
            <th>Metadata</th>
        </tr>
    <tr ng-repeat="(key,value) in data"  border="1">

        <td>{{key}}</td>
        <td ng-if="key !='BoundingBox'">{{value}}</td>

        <td ng-if="key =='BoundingBox'">
            <div ng-repeat="(k,v) in data.BoundingBox">
                {{k}} : 
                {{v}}
            </div>    
        </td>
    </tr>
</table>
        <a style="align:middle;" href="form">GO BACK</a>
        <a style="align:middle;" href="download/{{data.identifier}}">Download</a>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../../javascript/move.js"></script>
    </body>
</html>
