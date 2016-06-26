/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinh.vnsc.controllers;

/**
 *
 * @author chinhvm
 */
import chinh.vnsc.data.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrintMetadataController {
    private Library lib;

    public PrintMetadataController() throws Exception{
        this.lib = new Library();
    }
    
    @RequestMapping(value="/get-data",method = RequestMethod.POST)
    public String data(@RequestParam("searchid") String searchId,Model model) throws Exception{
        if(searchId == ""){
            model.addAttribute("metadata", "Invalid input");
        } else{
            int id = Integer.parseInt(searchId);
   
            if(id == 0 || id == 1){
                model.addAttribute("metadata", lib.getMetadata(id-1));
            } else{
                model.addAttribute("metadata", "We have not update that metadata");
            }
        }
        return "data";
    }
    
    @RequestMapping(value="/enter")
    public String home(Model model){
        model.addAttribute("name","Chinh");
        return "hello";
    }
    
}
