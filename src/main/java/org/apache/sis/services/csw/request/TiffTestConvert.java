/*
 * Copyright 2016 nguye.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sis.services.csw.request;

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
 * @author nguye
 */
public class TiffTestConvert {
/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
  *
  * @author chinhvm
  */
 
 
  
  
 /** 
 + * 
 + * @author chinhvm 
 + */ 
     String local;
     String tifFile;
     BufferedImage data;
 
     public BufferedImage getData() {
         return data;
     }
 
     public TiffTestConvert(String local, String tifFile) throws IOException {
         this.local = local;
         this.tifFile = tifFile;
         this.convert();
     }
 
     public void convert() throws IOException{ 
         //Way 2
        ImageInputStream input = ImageIO.createImageInputStream(new File(local+tifFile)); 
         
        System.out.println(input);
        BufferedImage tif = ImageIO.read(input);
        System.out.println(tif);
        ImageIO.write(tif, "png", new File(local+tifFile.substring(0,tifFile.length()-4)+".png"));
        data = ImageIO.read(new File(local+tifFile));
    }   
     
     /**
      *
      * @param args
      * @throws IOException
      */
     public static void main(String[] args) throws IOException {
         TiffTestConvert img = new TiffTestConvert("C:\\Users\\nguye\\Desktop\\projectGSOC\\data\\geotiff\\LC80360232016203LGN00","LC80360232016203LGN00_B1.TIF");
         System.out.println(img.getData());
     }
}     

