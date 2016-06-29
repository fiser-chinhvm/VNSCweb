/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

/**
 *
 * @author chinhvm
 */

import java.awt.image.BufferedImage; 
import java.awt.image.RenderedImage; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.Iterator; 
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam; 
import javax.imageio.ImageReader; 
import javax.imageio.stream.ImageInputStream; 

 
 
/** 
 * 
 * @author chinhvm 
 */ 
public class HandlePicture  { 
    String local;
    String tifFile;
    BufferedImage data;

    public BufferedImage getData() {
        return data;
    }

    public HandlePicture(String local, String tifFile) throws IOException {
        this.local = local;
        this.tifFile = tifFile;
        this.convert();
    }

    public void convert() throws IOException{ 
      
        //WAY 1 
//        File tiffFile = new File(local+"albers27.tif"); 
//        SeekableStream s = new FileSeekableStream(tiffFile); 
//        TIFFDecodeParam param = null; 
//        ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param); 
//        RenderedImage op = dec.decodeAsRenderedImage(0); 
//        FileOutputStream fos = new FileOutputStream(local+"albers27.jpg"); 
//        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos); 
//        jpeg.encode(op.getData()); 
//        fos.close(); 
         
        //Way 2
        ImageInputStream input = ImageIO.createImageInputStream(new File(local+tifFile)); 
        
        System.out.println(input);
        BufferedImage tif = ImageIO.read(input);
        System.out.println(tif);
        ImageIO.write(tif, "png", new File(local+tifFile.substring(0,tifFile.length()-4)+".png"));
        data = ImageIO.read(new File(local+tifFile));
    }   
    
    public static void main(String[] args) throws IOException {
        HandlePicture img = new HandlePicture("/home/chinhvm/Documents/","albers27.tif");
        System.out.println(img.getData());
    }
} 

