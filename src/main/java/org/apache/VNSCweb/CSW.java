/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import static com.sun.org.apache.xml.internal.resolver.Catalog.URI;
import static com.sun.xml.ws.security.policy.Header.URI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.apache.VNSC.controllers.HandlePicture;
import org.apache.VNSC.controllers.ProfileService;
import org.apache.VNSC.controllers.Record;
import org.apache.VNSCweb.model.GetCapabilitie;
import org.apache.VNSCweb.model.SummaryRecord;
import static org.glassfish.jersey.server.model.Parameter.Source.URI;

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
    public List<GetCapabilitie> getCapabilities(@QueryParam("REQUEST") String request,@QueryParam("AcceptVersion") String Version,@QueryParam("AcceptFormat") String format) {
        if( request =="GetCapabilities" && Version=="2.0.2,2.0.0,1.0.7" && format=="application/xml" ){
            return d.GetCapabilitiesRequest();
        }
        return d.GetCapabilitiesRequest();
    }
    
    @GET
    @Path("/PrintPicture")
    @Produces("image/png")
    public BufferedImage getImage() throws IOException{
        HandlePicture img = new HandlePicture("src/main/webapp/WEB-INF/resources/","albers27.tif");
        return img.getData();
    }
    
    @GET
    @Path("/DescribeRecord")
    @Produces(MediaType.APPLICATION_XML)
    public List<SummaryRecord> DescribeRecord() throws ParseException, Exception {
        Record record = new Record();
        return record.getAllRecord();
    }

    @GET
    @Path("/GetRecord/{format}")
    @Produces(value = {MediaType.APPLICATION_XML})
    public SummaryRecord GetRecords(@PathParam("format") String format) throws ParseException, Exception {
        ProfileService record = new ProfileService();
        return record.getProfile(format);
    }

    @GET
    @Path("/GetRecordByID/{messageId}")
    @Produces(MediaType.APPLICATION_XML)
    public SummaryRecord getRecordById(@PathParam("messageId") long id,@Context UriInfo uriInfor) throws ParseException, Exception {
        Record record = new Record();
        SummaryRecord a = record.getRecordById(id);
        a.addLink(getUriforSel(uriInfor,a), "sel");
        a.addLink(getUriforProfile(uriInfor,a), "profile");
        return a;
    }
    private String getUriforSel(UriInfo uriInfor, SummaryRecord record){
        String uri = uriInfor.getBaseUriBuilder()
                .path(CSW.class)
                .path(Long.toString(record.getId()))
                .build()
                .toString();
        
        return uri;
    }
    @GET
    public List<SummaryRecord> getRecordyear(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size) throws Exception {
         Record record = new Record();
        if (year > 0) {
            return record.getAllRecordForYear(year);
        }
        if (start >= 0 && size > 0) {
            return record.getAllRecordPaginated(start, size);
        }
        return record.getAllRecord();
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
        ProfileService record = new ProfileService();
        return record.addProfile(add);
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
    private String getUriforProfile(UriInfo uriInfor, SummaryRecord a) {
       URI uri = uriInfor.getBaseUriBuilder()
               .path(CSW.class)
               .path(CSW.class,"getRecordById")
//               .path(a.getFormat())
               .resolveTemplate("messageId", a.getId())
               .build();
               return uri.toString();
               }

    
}
