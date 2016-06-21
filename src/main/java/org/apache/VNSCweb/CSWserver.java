/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haonguyen
 */
public class CSWserver {
    List<String> bookList = new ArrayList<>();
    List<String> musicList = new ArrayList<>();
    List<String> movieList = new ArrayList<>();
    public CSWserver(){
        bookList.add("Inferno");
        bookList.add("222");
        bookList.add("333");
        musicList.add("444");
        musicList.add("555");
        musicList.add("666");
        movieList.add("777");
        movieList.add("888");
        movieList.add("999");
    }
    public List<String> GetCapabilities(){
    List<String> categories = new ArrayList<>();
    categories.add("Books");
    categories.add("Music");
    categories.add("Movies");
    return categories;
    } 
    public List<String> DescribeRecord(String category){
        switch (category.toLowerCase()){
            case "books":
                return bookList;
            case "music":
                return musicList;
            case "movie":
                return movieList;
        }
        return null;        
    }
    public boolean GetDomain(String category,String product){
     switch (category.toLowerCase()){
         case "books":
             bookList.add(product);
             break;
         case "music":
             musicList.add(product);
             break;
             case"movies":
                 movieList.add(product);
                 break;
             default:
                 return false;
     } 
     return true;
    }
}
