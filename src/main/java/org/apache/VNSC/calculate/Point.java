/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

/**
 *
 * @author BoyHaLoi
 */
public class Point {
    double longtitude;
    double latitude;

    public Point() {
        longtitude = 0;
        latitude = 0;
    }

    public Point(double longtitude, double latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }
    
    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }   
}
