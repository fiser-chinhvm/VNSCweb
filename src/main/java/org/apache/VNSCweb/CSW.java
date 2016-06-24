/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import java.util.List;
import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.JAXBException;
import org.apache.VNSCweb.controller.FileDownloadImpl;
import org.apache.VNSCweb.controller.CapabilitiesRequest;

/**
 *
 * @author haonguyen
 */
@WebService(serviceName = "CSW")
public class CSW {
 CSWserver a = new CSWserver();
 FileDownloadImpl b = new  FileDownloadImpl();
    @WebMethod(operationName = "GetCapabilities")
   public CapabilitiesRequest getCapabilities() throws JAXBException  {
        CapabilitiesRequest b = new CapabilitiesRequest();
        b.GetCapabilitiesRequest();
        return b;
    }
     @WebMethod(operationName = "DescribeRecord" )
    public List<String> DescribeRecord(@WebParam(name = "name") String category) {
        return a.DescribeRecord(category);
    }
    @WebMethod(operationName = "GetDomain")
    public boolean GetDomain( String category, String product) {
        return a.GetDomain(category, product);
    }
    @WebMethod
    public DataHandler FileDownload(@WebParam(name = "name") String fileName){
        return b.downloadFile(fileName);
    }
//    @WebMethod(operationName = "GetRecords")
//    public String GetRecords(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
//    @WebMethod(operationName = "GetRecordById")
//    public String GetRecordById(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
//    @WebMethod(operationName = "Harvest")
//    public String Harvest(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
//    @WebMethod(operationName = "Transaction")
//    public String Transaction(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
}
