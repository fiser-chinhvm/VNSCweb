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
</style>
<body>

<button type="button" onclick="loadDoc()">Get my CD collection</button>
<br><br>
<table id="demo"></table>
<script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
    myFunction(xhttp);
    }
  };
  xhttp.open("GET", "http://localhost:8080/MavenWebProject/VNSC/csw/GetRecord?format=GEOTIFF", true);
  xhttp.send();
}
function myFunction(xml) {
  var i;
  var xmlDoc = xml.responseXML;
  var table="<tr><th>Name</th><th>Format</th><th>Download</th></tr>";
  var x = xmlDoc.getElementsByTagName("SummaryRecord");
  for (i = 0; i <x.length; i++) { 
    table += "<tr><td>" +
    x[i].getElementsByTagName("title")[0].childNodes[0].nodeValue +
    "</td><td>" +
    x[i].getElementsByTagName("format")[0].childNodes[0].nodeValue +
    "</td><td>" +
    "<a href = 'http://localhost:8080/MavenWebProject/VNSC/csw/downloadgeotiff/"+x[i].getElementsByTagName("title")[0].childNodes[0].nodeValue+"'><button >Download</button></a>" +
    "</td></tr>";
  }
  document.getElementById("demo").innerHTML = table;
}
  </script>

</body>
</html>
