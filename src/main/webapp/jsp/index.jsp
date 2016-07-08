<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to VNSC web project</title>
        <link  rel="stylesheet" href="css/myweb.css" type="text/css">
         <script src="javascript/main.js" />"></script>
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
                    <form action="jsp/geotiff.jsp" method="POST" >
                        <p  class="post-infor">Format image</p>
                    
                    <content class = "text">
                        <input type="checkbox" name="format" value="GEOTIFF"> GeoTiff
                        <input type="checkbox" name="format" value="MODIS">   Modis
                    </content>
                        
                    <footer>
                        <p  class="post-infor">Date</p>
                    </footer>
                    <content class = "text">
                        Search from <input  name="date1" type="date" style="width: 130px"> to <input name="date2" type="date" style="width: 130px">
                      
                    </content>
                        <p  class="post-infor">Coordinates</p>
                    
                    <content class = "text">
                        <input  id="divId" type="text" style="width: 70%; height: 30px"  name="divid" > 
        
                    </content>
                        <br>
                        <br>
                        <div class="Button">
                            <input type="submit"  >
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

     
   
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBP1jtGKFo80FQZ9BC1nOD5c0c0Mhk4Ivo&callback=initMap">
    </script>
    

</body>
</html>
