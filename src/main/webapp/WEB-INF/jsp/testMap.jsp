<%-- 
    Document   : testMap
    Created on : Jul 6, 2016, 4:20:49 PM
    Author     : chinhvm
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
        <div ng-app="mapsApp" ng-controller="MapCtrl">
    <div id="map"></div>
    <div id="class" ng-repeat="marker in markers | orderBy : 'title'">
         <a href="#" ng-click="openInfoWindow($event, marker)">{{marker.title}}</a>
    </di
        
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBP1jtGKFo80FQZ9BC1nOD5c0c0Mhk4Ivo&callback=initMap">
    </script>
  <script type="text/javascript" src="../../javascript/testMap.js"></script>
    </body>
</html>
