/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinh.vnsc.data;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
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
    String local ="/home/chinhvm/Documents/";
    
    HandlePicture() throws IOException{
        //WAY 1
        File tiffFile = new File(local+"albers27.tif");
        SeekableStream s = new FileSeekableStream(tiffFile);
        TIFFDecodeParam param = null;
        ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
        RenderedImage op = dec.decodeAsRenderedImage(0);
        FileOutputStream fos = new FileOutputStream(local+"albers27.jpg");
        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
        jpeg.encode(op.getData());
        fos.close();
        
        //Way 2
//        ImageReader reader = ImageIO.getImageReadersByFormatName("TIFF").next();
//        ImageReadParam param = reader.getDefaultReadParam();
//        param.setSourceBands(new int[] {1, 2, 3});
//        param.setSourceSubsampling(4, 4, 0, 0);
//        ImageInputStream input = ImageIO.createImageInputStream(new File(local+"cea.jpg"));
//        Iterator<ImageReader> reader = ImageIO.getImageReaders(input);
//        System.out.print(reader.hasNext());
//        input.close();
    }

        
    
    public BufferedImage getImage() throws IOException{
        BufferedImage img = ImageIO.read(new File(local+"cea.jpg"));
        return img;
    }
    
    public static void main(String[] args) throws IOException {
        HandlePicture h = new HandlePicture();
        
    }
}
