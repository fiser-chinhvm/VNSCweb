<%-- 
    Document   : form
    Created on : Jul 3, 2016, 3:22:07 PM
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
    <style>
        .image{
            width:50px;
        }
    </style>
    <body ng-app="App">
        <div class="row">
        <div class="col-md-6" ng-controller="Ctrl">
        <form ng-submit="submit()" ng-if="!output">
                        <label>Format image</label>
                    <content class = "text">
                        <input type="checkbox" ng-model="search.format.geotiff" value="GEOTIFF"> GeoTiff
                        <input type="checkbox" ng-model="search.format.modis" value="MODIS">   Modis
                    </content><br>
                <fieldset ng-disabled="!search.format.geotiff && !search.format.modis">
                    <label>Name</label>
                    <input ng-model="search.identifier"/>
                            <footer>
                                <p  class="post-infor">Date</p>
                            </footer>
                            <content class = "text">
                                Search from <input ng-model="search.from" type="text" style="width: 120px"> 
                                to <input ng-model="search.to" type="text" style="width: 120px">
                               <br>
                                Search month <input type="month" ng-model="search.month">
                            </content>
                                <p  class="post-infor">Coordinates</p>

                            <content class = "text">
                                <input ng-model="search.coordinates.lat1" type="text" style="width: 300px; height: 30px;"> 

                            </content>
                                <br>
                                <br>
                                <div class="Button">
                                    <input class="button" type="submit" ng-click="submit()" name="REQUEST" value="submit">
                                </div>
                        
                </fieldset>
        </form>
             
            
            
            
            <div ng-if="output">
                <table class="table">
                    <thead>                     
                        <th>ID</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Type</th>
                    </thead><tbody>
                        <tr ng-repeat="item in output | filter:{identifier:search.identifier }| filter:bboxFilter" >
                            <td>{{item.id}}</td>
                            <td><img class="image" src="../../image/LC81240502014046LGN00.jpg"/></td>
                            <td><a href="move#/{{item.id}}">{{item.identifier}}</a></td>
                            <td>{{item.format}}</td>
                        </tr>
                    </tbody>
                </table>
                
                <button ng-click="back()">Search again</button>
                
         
                
            </div>
            <div class="col-md-6"></div>
             
       
        
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBP1jtGKFo80FQZ9BC1nOD5c0c0Mhk4Ivo&callback=initMap">
    </script>
        
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../../javascript/form.js"></script>
    </body>
</html>
