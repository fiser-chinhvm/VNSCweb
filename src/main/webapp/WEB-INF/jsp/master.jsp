<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
    
    <body ng-app="App">
    
<div ng-controller="myCtrl">

<p>Today's welcome message is:</p>

<!--<h1> {{data}} ssss</h1>-->
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
</div>

<p>The $http service requests a page on the server, and the response is set as the value of the "myWelcome" variable.</p>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../javascript/master.js"></script>
<!--<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
        $scope.test="aaaaaaa";
        $scope.ssss="aaaaaaa";
        
      $http.get(loc + "DescribeRecord")
      .then(function(response) {
          console.log("vao ham roi");
          $scope.myWelcome = response.data;
      });
    });
</script>-->
</body>
</html>