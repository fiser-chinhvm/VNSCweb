/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author haonguyen
 */
@WebService(serviceName = "CSW", targetNamespace="http://www.opengis.net/cat/csw/2.0.2")
public class CSW {
 CSWserver a = new CSWserver();
    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "hello")
//    public String hello(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
    @WebMethod(operationName = "GetCapabilities")
   public List<String> getCapabilities()  {
        return a.GetCapabilities();
    }
     @WebMethod(operationName = "DescribeRecord" )
    public List<String> DescribeRecord(@WebParam(name = "name") String category) {
        return a.DescribeRecord(category);
    }
    @WebMethod(operationName = "GetDomain")
    public boolean GetDomain( String category, String product) {
        return a.GetDomain(category, product);
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
