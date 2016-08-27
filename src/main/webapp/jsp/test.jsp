<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        
        <title>Welcome to Apache SIS web project</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Angular Material style sheet -->
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
    
    
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    
    <!--Animate-->
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

  <!-- Angular Material Library -->
  <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>
 
  <!-- Angular TABLE--> 
  <link rel="stylesheet" href="../lib/angular-material-data-table/dist/md-data-table.min.css">
  <script src="../lib/angular-material-data-table/dist/md-data-table.min.js"></script>
  
  <!--Project CSS-->
  <link  rel="stylesheet" href="../css/myweb.css" type="text/css">
  
  
    </head>

    <body ng-app="myApp" ng-controller="ctrl" class="mainBody" >
    <header class="mainheader">
        <div>
            <div class="links-top" layout="row"> 
                <span flex="70"></span>
                <span flex="10" class="link-box"><a class="link-text" href="#"><md-button>Home</a></span>
                <span flex="10" class="link-box"><a class="link-text" href="#"><md-button>Q&A</a></span>
                <span flex="10" class="link-box"><a class="link-text" href="#"><md-button>About</a></span>
            </div>
            <div layout="row" layout-align="center center" class="background-title">
                <div flex="30"><img style="width:80%; margin-left: 20px" src="../images/sis_logo_small.png"></div>
                <div flex="70"><marquee style="color:white; font-size:150%" direction="right"> Apache SIS web project</marquee></div>
            </div>
            <div class="links-bottom"></div>
        </div>
    </header>
            
    <div id="search-box" layout="row">
        <div flex="30" id="search" class="content">
            <div  class="post-info"><label>Search Result</label></div>
     <md-tabs md-dynamic-height md-border-bottom md-primary>
      <md-tab label="Basic">
        <md-content class="md-padding" style="background: #e8eee7">
           <form class="search-form">
                <md-input-container style="width:100%">
                    <label>Name</label>
                    <input ng-model="search.name">
                </md-input-container>
                <div class="row content-search" onchange="getCheckedCheckboxesFor()">
                    <md-input-container style="width:100%">
                      <label>Format</label>
                      <md-select ng-model="search.format" multiple>
                        <md-optgroup label="Landsat-8">
                          <md-option ng-value="item.type" ng-repeat="item in format.landsat">{{item.type}}</md-option>
                        </md-optgroup>
                        <md-optgroup label="Modis">
                          <md-option ng-value="item.type" ng-repeat="item in format.modis">{{item.type}}</md-option>
                        </md-optgroup>
                      </md-select>
                    </md-input-container>
                </div>

            <label style="font-size:20px;">Date</label>
                 <md-input-container style="width:100%">
                    <label>From</label>
                    <md-datepicker ng-model="search.startDate" placeHolder="From"></md-datepicker>
                </md-input-container>
           
                 <md-input-container style="width:100%">
                    <label>To</label>
                    <md-datepicker ng-model="search.rangeDate" placeHolder="From"></md-datepicker>
                </md-input-container>
            <!--</div>-->
           
            </form>
        </md-content>
      </md-tab>
         
      <md-tab label="Advanced">
        <md-content class="md-padding" style="background: #e8eee7">
          <div>
                <md-checkbox ng-model="isCloud" class="md-primary" aria-label="Finished?">
                    Cloud
                </md-checkbox>
            </div>
           <div ng-if="isCloud">
                <md-radio-group ng-model="search.cloud.type">
                    <md-radio-button class="md-primary" value="<">Bé hơn</md-radio-button>
                    <md-radio-button class="md-primary" value=">">Lớn hơn</md-radio-button>
                </md-radio-group>
                
                 <md-slider-container>
                    <md-input-container>
                       <input flex type="number" min="0" max="100" ng-model="search.cloud.value" aria-label="red" aria-controls="red-slider">
                    </md-input-container>
                    
                     <md-slider flex min="0" max="100" class="md-primary" ng-model="search.cloud.value" aria-label="red" id="red-slider">
                    </md-slider>
                 </md-slider-container>
            </div>
            <div>
                <md-input-container style="width:100%">
                    <label>Type</label>
                    <md-select ng-model="search.type" multiple>
                        <md-optgroup label="Landsat-8">
                          <md-option ng-value="item.type" ng-repeat="item in type.landsat">{{item.type}}</md-option>
                        </md-optgroup>
                        <md-optgroup label="MOD">
                          <md-option ng-value="item.type" ng-repeat="item in type.modis">{{item.type}}</md-option>
                        </md-optgroup>
                    </md-select>
                </md-input-container>
            </div>
        
        </md-content>
      </md-tab>
    </md-tabs>
            <center><md-button ng-click="submit()" class="md-primary md-raised">Gửi</md-button></center>
            
        </div>
                
        <div flex="70">     
            <div id="map" style="width:100%"></div>
        </div>   
    </div>
    
    <div id="result" style="display:none; padding: 20px; min-height:500px; background-color: #e8eee7">
        <md-button ng-click="backToSearch()" class="md-primary">Search Again</md-button>
        <center><md-progress-circular style="margin-bottom:30px" ng-show="result == 0" md-mode="indeterminate"></md-progress-circular></center>
        <center><h2 style="color: red" ng-show="result == null">NO RESULT FOUND</h2></center>

        <md-table-container ng-show="result != 0 && result != null">
          <table md-table ng-model="selected" md-progress="promise">
            <thead md-head md-order="query.order" md-on-reorder="logOrder">
              <tr md-row>
                <th md-column><span>STT</span></th>
                <th md-column><span>Image</span></th>
                <th md-column md-order-by="format" md-desc><span>Type</span></th>
                <th md-column md-order-by="identifier"><span>Name</span></th>
                <th md-column><span>Download</span></th>
              </tr>
            </thead>
            <tbody md-body id="result-table">
              <tr md-row md-select="item" md-auto-select="options.autoSelect" ng-repeat="item in result | filter: filter.search | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit">
                <td md-cell ng-click="enterItem(item)">{{$index + 1 + (query.page-1) * query.limit}}</td>
                <td md-cell ng-click="enterItem(item)">{{item.publisher}}</td>
                <td md-cell ng-click="enterItem(item)">{{item.format}}</td>
                <td md-cell ng-click="enterItem(item)">{{item.identifier}}</td>
                <td md-cell><a href = "http://localhost:8084/sis/VNSC/csw/2.0.2/download/{{item.identifier}}"><md-button class="md-primary">Download</md-button></a></td>
              </tr>
            </tbody>
          </table>
        </md-table-container>
        
        <md-table-pagination ng-show="result != 0 && result != null" md-limit="query.limit" md-limit-options="limitOptions" md-page="query.page" md-total="{{result.length}}" md-page-select="options.pageSelect" md-boundary-links="options.boundaryLinks" md-on-paginate="logPagination"></md-table-pagination>
        
    </div>
    
    <div id="detail" style="display:none; padding: 20px; min-height:500px; background-color: #e8eee7">
        <md-button style="margin-bottom:20px" ng-click="backToResult()" class="md-primary">Back To Table</md-button>
        <md-progress-linear ng-show="detail == 0" md-mode="indeterminate"></md-progress-linear>
        
        <div layout="row" ng-show="detail != 0">
            <div flex="60">
                <md-table-container >
                    <table md-table ng-model="selected" md-progress="promise">
                        <thead md-head>
                            <tr md-row>
                                <td md-cell>Field</td>
                                <td md-cell>Value</td>
                            </tr>
                        </thead>
                        <tbody md-body id="detail-table">
                            <tr md-row ng-show="$index > 1" ng-repeat="(key, value) in detail">
                                <td md-cell> {{key}} </td> 
                                <td md-cell> {{ value }} </td>
                            </tr>
                        </tbody>
                    </table>
                </md-table-container>
            </div>
            <div flex="10"></div>
                <div flex="30" id="details-geotiff-download">
                    <div class="link-download">
                        <span ng-click="downloadDetail()"><a id="_B1.TIF" href="#"><md-button>Band 1</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B2.TIF" href="#"><md-button>Band 2</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B3.TIF" href="#"><md-button>Band 3</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B4.TIF" href="#"><md-button>Band 4</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B5.TIF" href="#"><md-button>Band 5</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B6.TIF" href="#"><md-button>Band 6</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B7.TIF" href="#"><md-button>Band 7</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B8.TIF" href="#"><md-button>Band 8</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B9.TIF" href="#"><md-button>Band 9</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B10.TIF" href="#"><md-button>Band 10</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_B11.TIF" href="#"><md-button>Band 11</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id="_MTL.txt" href="#"><md-button>.txt</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id=".tar.gz" href="#"><md-button>Package</md-button></a></span><br>
                    </div>
                </div>
            
                <div id="details-modis-download" flex="30">
                    <div class="link-download">
                        <!--<span ng-click="downloadDetail()"><a id=".jpg" href="#"><md-button>JPG</md-button></a></span><br>-->
                        <span ng-click="downloadDetail()"><a href="#"><md-button>HDF</md-button></a></span><br>
                        <span ng-click="downloadDetail()"><a id=".xml" href="#"><md-button>XML</md-button></a></span><br>
                    </div>
                </div>
            </div>
            
        </div>
        
    </div>
    
    <footer class="mainfooter">
        <p>CopyRight &copy; VNSC.com</p>
    </footer>

      <script src="../javascript/main.js" /></script>
   
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBP1jtGKFo80FQZ9BC1nOD5c0c0Mhk4Ivo&callback=initMap">
    </script>
    

</body>
</html>