/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinh.vnsc.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.opengis.util.FactoryException;

/**
 *
 * @author chinhvm
 */
public class Library {
    private ArrayList<Metadata> data = new ArrayList<Metadata>();
    
    public Library() throws IOException, DataStoreException, JAXBException, ParseException, FactoryException {
        Metadata md1;
        Metadata md2;
        LandsatReader reader;
        try (BufferedReader in = new BufferedReader(new FileReader("/home/haonguyen/data/LC81230522014071LGN00_MTL.txt"))) {
            reader = new LandsatReader(in);
            md1 = reader.read();
            data.add(md1);
        }
        md2 = (Metadata) XML.unmarshal(new File("/home/haonguyen/data/G184220810-LAADS.iso19115"));
        data.add(md2);
    }
    
    public Metadata getMetadata(int id){
        return data.get(id);
    }
}
