<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to VNSC web project</title>
        <link  rel="stylesheet" href="css/myweb.css" type="text/css">
        <meta name="viewport" content="width=devide-width,initial-scale=1.0"
    </head>

    <body class="body" >
    <header class ="mainheader">
        <img src="image/image.png">
        <marquee direction="right" ><h2 style="color: #fd1616">TRUNG TÂM VỆ TINH QUỐC GIA - VIETNAM NATIONAL SATELLITE CENTER</h2></marquee>
        <nav><ul>
                <li><a href="" class="active">Home</a></li>
                <li><a href="">About</a></li>
                <li><a href="">Contact</a></li>
            </ul></nav> 
    </header>
    <div class="mainContent" >
        <div  class="content">
            <article class="topcontent">
                <header>
                    <h2><a href="" title="Firt post">Search metadata</a></h2>
                    <form>
                        <p  class="post-infor">Format image</p>
                    
                    <content class = "text">
                        <input type="checkbox" name="type" value="GEOTIFF"> GeoTiff
                        <input type="checkbox" name="type" value="MODIS">   Modis
                    </content>
                        
                    <footer>
                        <p  class="post-infor">Date</p>
                    </footer>
                    <content class = "text">
                        Search from <input type="date" style="width: 120px"> to <input type="date" style="width: 120px">
                       <br>
                        Search month <input type="month">
                    </content>
                        <p  class="post-infor">Coordinates</p>
                    
                    <content class = "text">
                        <input type="text" style="width: 300px; height: 30px   "> 
                        
                    </content>
                        <br>
                        <br>
                        <div class="Button">
                        <input class="button" type="submit" name="REQUEST" value="submit">
                        </div>
                    </form>
                </header>
            </article>
        </div>
    </div>
    <aside class="top-sidebar">
        <article>
            <div id="map"></div>
        </article>
    </aside>
    <footer class="mainfooter">
        <p>CopyRight &copy; VNSC.com</p>
    </footer>

     
    <script>
      function initMap() {
        var mapDiv = document.getElementById('map');
        var map = new google.maps.Map(mapDiv, {
            center: {lat: 19.111061,  lng:  105.451661},
            zoom:6
        });
      }
    </script>
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBP1jtGKFo80FQZ9BC1nOD5c0c0Mhk4Ivo&callback=initMap">
    </script>
</body>
</html>
