var app = angular.module("myApp", ['ngMaterial', 'md.data.table']);
var rectangle;
var map;
var infoWindow;
var bbox = ["", "", "", ""];

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 19.111061, lng: 105.451661},
        zoom: 6
    });

    var bounds = {
        north: 19.111061,
        south: 18.111061,
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
    
    bbox[0] = sw.lng();
    bbox[1] = ne.lng();
    bbox[2] = sw.lat();
    bbox[3] = ne.lat();
    
//    document.getElementById("divId").value = content;

    // Set the info window's content and position.
    infoWindow.setContent(contentString);
    infoWindow.setPosition(ne);

    infoWindow.open(map);
}

function getCheckedCheckboxesFor() {
    var checkboxes = document.querySelectorAll('input[name="formatType"]:checked'), values = [];
    Array.prototype.forEach.call(checkboxes, function(el) {
        values.push(el.value);
    });
    console.log(values);
    document.getElementById("format").value = values;
}

app.controller("ctrl", function($scope, $http, $q, $timeout, $location) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.format = {landsat: [{type: 'GEOTIFF'}], modis: [{type: 'MOD11A2'}, {type: 'MOD09GA'}, {type: 'MYD09A1'}]};
    $scope.type = {landsat: [{type: 'LT1'}], modis: [{type: 'L1'}, {type: 'L2'}, {type: 'L3'}]};
    $scope.search = {name: "", format: "", startDate: "", rangeDate: "", type: "", cloud: {type: "", value: ""} };
    $scope.result = 0;
    $scope.detail = 0;
    
    var link = "http://localhost:8084/sis/VNSC/csw/2.0.2/getrecords?service=CSW&version=2.0.2&request=GetRecords&elementSetName=full&constraintLanguage=filter";
    
    $scope.backToSearch = function() {
        $scope.result = 0;
        $('#search-box').show();
        $('#result').hide();
    }
    
    $scope.backToResult = function () {
        $scope.detail = 0;
        $('#result').show();
        $('#detail').hide();
        console.log($scope.detail)
    }
    
    $scope.submit = function() {
        var startDate = "";
        var rangeDate = "";
        
        console.log(bbox);
        
        if($scope.search.rangeDate != "" && $scope.search.startDate != "") {
            startDate = $scope.search.startDate.toLocaleDateString("fr-CA");
            rangeDate = $scope.search.rangeDate.toLocaleDateString("fr-CA");
        }
        
        
        console.log(link + "&identifier=" + $scope.search.name + "&format=" + $scope.search.format + "&west=" + bbox[0] + "&east=" + bbox[1] + "&south=" + bbox[2] + "&north=" + bbox[3] + "&startDate=" + startDate + "&rangeDate=" + rangeDate + "&type=" + $scope.search.type + "&cloud=" + $scope.search.cloud.type + $scope.search.cloud.value);
        $('#search-box').slideUp();
        $('#result').show();
        
        $http.get(link + "&identifier=" + $scope.search.name + "&format=" + $scope.search.format + "&west=" + bbox[0] + "&east=" + bbox[1] + "&south=" + bbox[2] + "&north=" + bbox[3] + "&startDate=" + startDate + "&rangeDate=" + rangeDate + "&cloud=" + $scope.search.cloud.type + $scope.search.cloud.value)
        .then(function(response) {
            $scope.result = response.data.SearchResults.Record;
            console.log($scope.result);
        });
    }
    
    $scope.getDate = function(){
        console.log();
    }
   
  $scope.selected = [];
  $scope.limitOptions = [5, 10, 15];
  
  $scope.options = {
    rowSelection: true,
    multiSelect: false,
    autoSelect: true,
    decapitate: false,
    limitSelect: true,
    pageSelect: true
  };
  
  $scope.query = {
    order: 'name',
    limit: 5,
    page: 1
  };
  
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
  
  var identifier;
  
  $scope.enterItem = function (item) {
    console.log(item.identifier, 'was selected');
    identifier = item.identifier;
    if(identifier.startsWith("LC")) {
        $('#details-geotiff-download').show();
        $('#details-modis-download').hide();
    }
    
    if(identifier.startsWith("M")) {
        $('#details-geotiff-download').hide();
        $('#details-modis-download').show();
    }
    
    $('#result').slideUp();
    $('#detail').show();
    
    var detailLink = "http://localhost:8084/sis/VNSC/csw/2.0.2/getrecordbyid?service=CSW&version=2.0.2&request=GetRecordById&elementSetName=full&Id=";
    $http.get(detailLink +item.identifier).then(function(res) {
        $scope.detail = res.data.Record;
        console.log($scope.detail);
    });
  };
  
  
  $scope.logOrder = function (order) {
    console.log('order: ', order);
  };
  
  
  $scope.logPagination = function (page, limit) {
    console.log('page: ', page);
    console.log('limit: ', limit);
  }
  
    $scope.downloadDetail = function() {
         
        console.log($('#link-geotiff').children('a'));

        $('.link-download').find('a').each(function () {
            console.log(this.id);
            $(this).attr("href", "http://localhost:8084/sis/VNSC/csw/2.0.2/download/" + identifier + this.id)// "this" is the current element in the loop
        });


    } 
});