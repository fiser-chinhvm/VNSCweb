var loc = "http://localhost:8084/MavenWebProject/VNSC/csw/";

var app = angular.module('App', []);

    app.controller('Ctrl', function($scope, $http, $location) {
        $scope.search={identifier:"",format:{geotiff:true, modis:true}, from:5, to:"", month:"", coordinates:{long1:"",lat1:"", long2:"",lat2:""}};
        $scope.print = "";
//      $http.get(loc + "GetRecord/GEOTIFF")
//      .then(function(response) {
//         
//          $scope.data = response.data;
//          console.log($scope.data);
//          $scope.bbox = $scope.data.BoundingBox;
//      });

        $scope.submit= function(){
//                $scope.checkFormat = true;
                if($scope.search.format.geotiff == true && $scope.search.format.modis == false){
                    $http.get(loc+"GetRecordByFormat/GEOTIFF").then(function(response){
                        $scope.output = response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });   
                }
//                
                if($scope.search.format.modis == true && $scope.search.format.geotiff == false){
                    $http.get(loc+"GetRecordByFormat/MOD021KM").then(function(response){
                        $scope.output= response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });
                }
                
                if($scope.search.format.geotiff == true && $scope.search.format.modis == true){
                    $http.get(loc+"DescribeRecord").then(function(response){
                        $scope.output= response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });
                }
        }
           
            
        $scope.test=function(a){
            var log;
            angular.forEach(a,function(key,value){
                var a;
                a = key.BoundingBox;
//                console.log(a.eastBoundLongitude);
                return a;
            });
        }
        
        $scope.bboxFilter = function(a){
            console.log($scope.search.coordinates);
            var east = parseFloat(a.BoundingBox.eastBoundLongitude);
            var south = parseFloat(a.BoundingBox.southBoundLongitude);
            var north = parseFloat(a.BoundingBox.northBoundLongitude);
            var west = parseFloat(a.BoundingBox.westBoundLongitude);
            
            var searchLat1 = parseFloat($scope.search.coordinates.lat1);
            var searchLong1 = parseFloat($scope.search.coordinates.long1);
            var searchLat2 = parseFloat($scope.search.coordinates.lat2);
            var searchLong2 = parseFloat($scope.search.coordinates.long2);
//           console.log(searchLong+" "+searchLat+"affter" +east +" " +south+ " " + north+" "+west);
            if($scope.search.coordinates.lat2 == "" &&
                    $scope.search.coordinates.long1 =="" &&
                    $scope.search.coordinates.lat1 =="" &&
                    $scope.search.coordinates.long2 == ""){console.log("vao ham roi");return true};
            if(searchLong1 < east && searchLong1 > west && searchLat1 > south && searchLat1 < north){
                return true;
                 console.log(searchLong1+" "+searchLat1+"affter" +east +" " +south+ " " + north+" "+west);
            }
            
            if(searchLong1 < east && searchLong1 > west && searchLat2 > south && searchLat2 < north){
                return true;
                
            }
            
            if(searchLong2 < east && searchLong2 > west && searchLat1 > south && searchLat1 < north){
                return true;
                
            }
            
            if(searchLong2 < east && searchLong2 > west && searchLat2 > south && searchLat2 < north){
                return true;
                 
            }
            
            return false;
        }
        $scope.back = function(){
            $scope.output = "";
        }
        
        
    });
    
    
      var rectangle;
      var map;
      var infoWindow;

      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 19.111061,  lng:  105.451661},
          zoom: 6
        });

        var bounds = {
          north:  19.111061,
          south:  18.111061,
          east: 105.451661,
          west: 104.451661
        };

        // Define the rectangle and set its editable property to true.
        rectangle = new google.maps.Rectangle({
          bounds: bounds,
          editable: true,
          draggable: true
        });

        rectangle.setMap(map);

        // Add an event listener on the rectangle.
        rectangle.addListener('bounds_changed', showNewRect);

        // Define an info window on the map.
        infoWindow = new google.maps.InfoWindow();
      }
      // Show the new coordinates for the rectangle in an info window.

      /** @this {google.maps.Rectangle} */
      function showNewRect(event) {
        var ne = rectangle.getBounds().getNorthEast();
        var sw = rectangle.getBounds().getSouthWest();

        var contentString = '<b>Rectangle moved.</b><br>' +
            'New north-east corner: ' + ne.lat() + ', ' + ne.lng() + '<br>' +
            'New south-west corner: ' + sw.lat() + ', ' + sw.lng();
     var content = ne.lat() + ', ' + ne.lng() + ', '
             + sw.lat() + ', ' + sw.lng();
                  
            document.getElementById("divId").value=content;

        // Set the info window's content and position.
        infoWindow.setContent(contentString);
        infoWindow.setPosition(ne);

        infoWindow.open(map);
      }
      
      var loc = "http://localhost:8084/MavenWebProject/VNSC/csw/";

var app = angular.module('App', []);

    app.filter('format', function() {
    return function(movies,genres) {
      var out = [];
      // Filter logic here, adding matches to the out var.
      return out;
    }
  });
    app.controller('Ctrl', function($scope, $http, $location) {
        $scope.search={identifier:"",format:{geotiff:true, modis:true}, from:5, to:"", month:"", coordinates:{long1:"",lat1:"", long2:"",lat2:""}};
        $scope.print = "";
//      $http.get(loc + "GetRecord/GEOTIFF")
//      .then(function(response) {
//         
//          $scope.data = response.data;
//          console.log($scope.data);
//          $scope.bbox = $scope.data.BoundingBox;
//      });

        $scope.submit= function(){
//                $scope.checkFormat = true;
                if($scope.search.format.geotiff == true && $scope.search.format.modis == false){
                    $http.get(loc+"GetRecordByFormat/GEOTIFF").then(function(response){
                        $scope.output = response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });   
                }
//                
                if($scope.search.format.modis == true && $scope.search.format.geotiff == false){
                    $http.get(loc+"GetRecordByFormat/MOD021KM").then(function(response){
                        $scope.output= response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });
                }
                
                if($scope.search.format.geotiff == true && $scope.search.format.modis == true){
                    $http.get(loc+"DescribeRecord").then(function(response){
                        $scope.output= response.data.SummaryRecord;
                        console.log($scope.test($scope.output));
                    });
                }
        }
           
            
        $scope.test=function(a){
            var log;
            angular.forEach(a,function(key,value){
                var a;
                a = key.BoundingBox;
//                console.log(a.eastBoundLongitude);
                return a;
            });
        }
        
        $scope.bboxFilter = function(a){
            console.log($scope.search.coordinates);
            var east = parseFloat(a.BoundingBox.eastBoundLongitude);
            var south = parseFloat(a.BoundingBox.southBoundLongitude);
            var north = parseFloat(a.BoundingBox.northBoundLongitude);
            var west = parseFloat(a.BoundingBox.westBoundLongitude);
            
            var searchLat1 = parseFloat($scope.search.coordinates.lat1);
            var searchLong1 = parseFloat($scope.search.coordinates.long1);
            var searchLat2 = parseFloat($scope.search.coordinates.lat2);
            var searchLong2 = parseFloat($scope.search.coordinates.long2);
//           console.log(searchLong+" "+searchLat+"affter" +east +" " +south+ " " + north+" "+west);
            if($scope.search.coordinates.lat2 == "" &&
                    $scope.search.coordinates.long1 =="" &&
                    $scope.search.coordinates.lat1 =="" &&
                    $scope.search.coordinates.long2 == ""){console.log("vao ham roi");return true};
            if(searchLong1 < east && searchLong1 > west && searchLat1 > south && searchLat1 < north){
                return true;
                 console.log(searchLong1+" "+searchLat1+"affter" +east +" " +south+ " " + north+" "+west);
            }
            
            if(searchLong1 < east && searchLong1 > west && searchLat2 > south && searchLat2 < north){
                return true;
                
            }
            
            if(searchLong2 < east && searchLong2 > west && searchLat1 > south && searchLat1 < north){
                return true;
                
            }
            
            if(searchLong2 < east && searchLong2 > west && searchLat2 > south && searchLat2 < north){
                return true;
                 
            }
            
            return false;
        }
        $scope.back = function(){
            $scope.output = "";
        }
        
        
    });
    
    
    
    
   
    
    
    
    