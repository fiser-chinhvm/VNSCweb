/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import com.sun.jersey.api.view.Viewable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.VNSC.controllers.CapabilitiesRequest;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.apache.VNSC.controllers.AnyText;
import org.apache.VNSC.controllers.ReadConfiguePath;
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
    ReadConfiguePath path = new ReadConfiguePath();
    @GET
    @Path("/GetCapabilities")
    @Produces(MediaType.APPLICATION_XML)
    public List<GetCapabilitie> getCapabilities(@QueryParam("REQUEST") String request, @QueryParam("AcceptVersion") String Version, @QueryParam("AcceptFormat") String format) {
        if (request == "GetCapabilities" && Version == "2.0.2,2.0.0,1.0.7" && format == "application/xml") {
            return d.GetCapabilitiesRequest();
        }
        return d.GetCapabilitiesRequest();
    }
    @GET
    @Path("/image/{name}")
    @Produces({"image/png", "image/jpg"})
    public Response getFullImage(@PathParam("name") String name) throws IOException {
        ImageInputStream input = ImageIO.createImageInputStream(new File(path.getPropValues()+"/image/"+name));
        ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(5, 5, 0, 0);
        reader.setInput(input);
        BufferedImage image = reader.read(0, param);
		
        return Response.ok(image).build();
    }
    
    @GET
    @Path("/test")
    public Response test() throws ParseException, Exception {
        return Response.ok(new Viewable("/testMap")).build();
    }
    
    @GET
    @Path("/DescribeRecord")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SummaryRecord> DescribeRecord() throws ParseException, Exception {
        Record record = new Record();
        return record.getAllRecord();
    }
    @GET
    @Path("/move")
//    @Produces("text/html")
    public Response move() throws Exception{       
        Map<String,String> data = new HashMap<>();
        data.put("fr", "tesst");
        data.put("t", "anotherTest");
        return Response.ok(new Viewable("/move",data)).build();
    }
    
    @GET
    @Path("/form")
    public Response form(){
        return Response.ok(new Viewable("/form")).build();
    }
    
    @GET
    @Path("/GetRecordByFormat/{format}")
    @Produces(value = {MediaType.APPLICATION_XML})
    public List<SummaryRecord> GetRecords(@PathParam("format") String format) throws ParseException, Exception {
        Record a = new Record();
        return a.getRecordByText(format);
    }
    
    @GET
    @Path("/GetRecordByID/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public List<SummaryRecord> GetRecords(@PathParam("id") long id) throws ParseException, Exception {
        Record a = new Record();
        return a.getRecordById(id);
    }
//    
//    @GET
//    @Path("/test")
//    @Produces("text/html")
//    public Response index() throws Exception {
//       return Response.ok(new Viewable("/master")).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<SummaryRecord> getRecordById(@QueryParam("GetRecordById") long id, @Context UriInfo uriInfor) throws ParseException, Exception {
        Record record = new Record();
        List<SummaryRecord> a = record.getRecordById(id);
//        a.addLink(getUriforSel(uriInfor,a), "sel");
//        a.addLink(getUriforProfile(uriInfor,a), "profile");
        return a;
    }

    private String getUriforSel(UriInfo uriInfor, SummaryRecord record) {
        String uri = uriInfor.getBaseUriBuilder()
                .path(CSW.class)
                .path(Long.toString(record.getId()))
                .build()
                .toString();

        return uri;
    }
    
    @GET
    public List<SummaryRecord> getRecordAllField(
        @QueryParam("format") String format,
        @QueryParam("identifier") String identifier,
        @QueryParam("west") double west,
        @QueryParam("east") double east,
        @QueryParam("south") double south,
        @QueryParam("north") double north, 
        @QueryParam("startDate") String date1, 
        @QueryParam("rangeDate") String date2) throws Exception{
        
        AnyText record = new AnyText(format, identifier, date1, date2);
        record.setBbox(west, east, south, north);
        record.setBbox(5,130 , 5, 130);
        record.filter();
     
        return record.getData();
    }
    
    @GET
    @Path("/GetRecord")
    public List<SummaryRecord> getRecordyear(@QueryParam("format") String format, @QueryParam("date1") String date1, @QueryParam("date2") String date2, @QueryParam("start") int start,@QueryParam("size") int size) throws Exception {
        Record record = new Record();
        if (format != null) {
            return record.getRecordByText(format);
        }
        if (date1.equals(date1) && date2.equals(date2)) {
            return record.SearchDate(date1, date2);
        }
        if (start >= 0 && size > 0) {
            return record.getAllRecordPaginated(start, size);
        }
       
        return record.getAllRecord();
    }

    @GET
    @Path("/download/{name}")
    @Produces("text/plain")
    public Response getFileGeotiff(@PathParam("name") String name) throws IOException {
        File file = new File(path.getPropValues()+"/" + name);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=" + name);
        return response.build();
    }


    private String getUriforProfile(UriInfo uriInfor, SummaryRecord a) {
        URI uri = uriInfor.getBaseUriBuilder()
                .path(CSW.class)
                .path(CSW.class, "getRecordById")
                .resolveTemplate("messageId", a.getId())
                .build();
        return uri.toString();
    }

}
