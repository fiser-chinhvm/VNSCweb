/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import java.io.File;
import java.text.ParseException;
import org.apache.VNSC.controllers.CapabilitiesRequest;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.VNSC.controllers.G;
import org.apache.VNSC.controllers.Record;
import org.apache.VNSCweb.model.GetCapabilitie;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
@Path("/csw")
public class CSW {

    CapabilitiesRequest d = new CapabilitiesRequest();
    @GET
    @Path("/GetCapabilities")
  @Produces(MediaType.APPLICATION_XML)
    public List<GetCapabilitie> getCapabilities() {

        return d.GetCapabilitiesRequest();
    }

    @GET
    @Path("/DescribeRecord")
    @Produces(MediaType.APPLICATION_XML)
    public List<SummaryRecord> DescribeRecord() throws ParseException, Exception {
        Record record = new Record();
        return record.getAllRecord();
    }
    @GET
    @Path("/DescribeRecord1")
    @Produces(MediaType.APPLICATION_XML)
    public List<SummaryRecord> GetRecord() throws ParseException, Exception {
        G record = new G();
        return record.getAllRecord();
    }
//    @POST
//    public boolean GetDomain( String category, String product) {
//        return a.GetDomain(category, product);
//    }
//    @WebMethod
//    public DataHandler FileDownload(@WebParam(name = "name") String fileName){
//        return b.downloadFile(fileName);
//    }

    @POST
    @Path("/DescribeRecord1/{type}")
    @Produces(value = {MediaType.APPLICATION_XML})
    public SummaryRecord GetRecords(@PathParam("type") String type) throws ParseException, Exception {
        G record = new G();
        return record.getRecord(type);
    }

    @GET
    @Path("/DescribeRecord/{messageId}")
    @Produces(MediaType.APPLICATION_XML)
    public SummaryRecord getRecordById(@PathParam("messageId") long id) throws ParseException, Exception {
        Record record = new Record();
        return record.getRecordById(id);
    }
//    @POST
//    public String Harvest(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
//    @POST
//    public String Transaction(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }

    @POST
    @Path("/DescribeRecord")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public SummaryRecord addRecord(SummaryRecord add) throws ParseException, Exception {
        Record record = new Record();
        return record.addRecord(add);
    }

    @PUT
    @Path("/DescribeRecord/{messageId}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public SummaryRecord updateRecord(@PathParam("messageId") long id, SummaryRecord add) throws ParseException, Exception {
        Record record = new Record();
        add.setId(id);
        return record.updateRecord(add);
    }

    @DELETE
    @Path("/DescribeRecord/{messageId}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void delRecord(@PathParam("messageId") long id) throws ParseException, Exception {
        Record record = new Record();

        record.removeRecord(id);
    }

    @GET
    @Path("/download/{name}")
    @Produces("text/plain")
    public Response getFile(@PathParam("name") String name) {
        File file = new File("/home/haonguyen/data/" + name);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=DisplayName-" + name);
        return response.build();
    }
}


