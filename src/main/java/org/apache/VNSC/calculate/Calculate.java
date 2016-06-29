/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;
import calculate.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BoyHaLoi
 */
public class Calculate {
    double R = 6378.137;
    double oneDegreeLat = 111.2;
    /**
     * @param args the command line arguments
     */
    public double distance(double lat1, double lon1, double lat2, double lon2){
        double dLat = (lat2 - lat1) * Math.PI/180;
        double dLong = (lon2 - lon1) * Math.PI/180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLong/2) * Math.sin(dLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d; 
    }
     
    public ArrayList<Point> calculatePoint(double lon, double lat, double radius){
        ArrayList<Point> point = new ArrayList<Point>();
        Point topLeftCorner = new Point(lon+radius/111,lat - radius/10); 
        return point;
    }
    
    
    public double oneDegreeLongBaseOnLat(double lat){
        double result = Math.PI/180 * Math.cos(lat) * R;
        return result;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
       Calculate test = new Calculate();
       System.out.println(test.distance(22, 105, 21, 105)+ "  " + test.oneDegreeLongBaseOnLat(25));
        
    }
    
    
    
}
