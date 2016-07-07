<!DOCTYPE html>
<html>
<style>
table,th,td {
  border : 1px solid black;
  border-collapse: collapse;
}

th,td {
  padding: 5px;
}

.image{
    height:50px;
    width:50px;
}
</style>
<body>

<button type="button" onclick="loadDoc()">Get my CD collection</button>
<br><br>
<table id="demo"></table>
<table id="detail"></table>
<script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
        
    myFunction(xhttp);
    }
  };
  xhttp.open("GET", "http://localhost:8084/MavenWebProject/VNSC/csw/GetRecord?format=GEOTIFF", true);
  xhttp.send();
  
}

function myFunction(xml) {
  var i;
  var xmlDoc = xml.responseXML;
   
  var table="<tr><th>STT</th><th>Image</th><th>Name</th><th>Format</th><th>Download</th></tr>";
 
  var x = xmlDoc.getElementsByTagName("SummaryRecord");
//   console.log(x[0].getElementsByTagName("dc:identifier")[0].childNodes[0].nodeValue);
//   console.log(x[0].getElementsByTagName("dc:format")[0].childNodes[0].nodeValue);
  for (i = 0; i <x.length; i++) {
    var stt =i+1;
    table += "<tr><td>" +
    stt+
    "</td><td>"+       
    "<img class='image' src='http://localhost:8084/MavenWebProject/VNSC/csw/image/"+
    x[i].getElementsByTagName("dc:identifier")[0].childNodes[0].nodeValue +".jpg'/>"+
    "</td><td>" +
    "<a href='#' onclick='loadMetadata("+x[i].getElementsByTagName("dc:id")[0].childNodes[0].nodeValue+")'>"+
    x[i].getElementsByTagName("dc:title")[0].childNodes[0].nodeValue+"</a>"+
    "</td><td>" +
    x[i].getElementsByTagName("dc:format")[0].childNodes[0].nodeValue +
    "</td><td>" +
    "<a href = 'http://localhost:8084/MavenWebProject/VNSC/csw/download/"+x[i].getElementsByTagName("dc:title")[0].childNodes[0].nodeValue+"'><button >Download</button></a>" +
    "</td></tr>";
  }
//  console.log(table);
  document.getElementById("demo").innerHTML = table;
}

function loadMetadata(id){
    document.getElementById("demo").innerHTML = "";
    var getXML = new XMLHttpRequest();
    
    getXML.onreadystatechange = function(){
         if (getXML.readyState == 4 && getXML.status == 200) { 
             console.log(getXML);
            tableMetadata(getXML);
        }
    }
    getXML.open("GET", "http://localhost:8084/MavenWebProject/VNSC/csw/GetRecordByID/"+id, true);
    getXML.send();    
}

function tableMetadata(x){
    var doc = x.responseXML;
    var metadata = doc.getElementsByTagName("SummaryRecord")[0];
    var bbox = metadata.getElementsByTagName("ows:BoundingBox");
    
    console.log(bbox[0].getElementsByTagName("ows:eastBoundLongitude")[0].childNodes[0].nodeValue);
    var table = "<tr><th>Field</th><th>Detail</th></tr>";
    table +="<tr><td>ID</td><td>"+metadata.getElementsByTagName("dc:id")[0].childNodes[0].nodeValue+"</td></tr>"+ 
            "<tr><td>Name</td><td>"+metadata.getElementsByTagName("dc:identifier")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>Format</td><td>"+metadata.getElementsByTagName("dc:format")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>Date Modified</td><td>"+metadata.getElementsByTagName("dct:modified")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>Type</td><td>"+metadata.getElementsByTagName("dc:type")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>east</td><td>"+bbox[0].getElementsByTagName("ows:eastBoundLongitude")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>west</td><td>"+bbox[0].getElementsByTagName("ows:westBoundLongitude")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>south</td><td>"+bbox[0].getElementsByTagName("ows:southBoundLongitude")[0].childNodes[0].nodeValue+"</td></tr>"+
            "<tr><td>north</td><td>"+bbox[0].getElementsByTagName("ows:northBoundLongitude")[0].childNodes[0].nodeValue+"</td></tr>";
    
    
    document.getElementById("detail").innerHTML = table;
}
  </script>

</body>
</html>
