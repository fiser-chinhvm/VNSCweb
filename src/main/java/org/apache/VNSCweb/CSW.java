
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.VNSCweb;

import org.apache.sis.services.csw.ConfigurationReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.sis.services.csw.request.DescribeRecordRequest;
import org.apache.sis.services.csw.request.GetCapabilitieRequest;
import org.apache.sis.services.csw.RecordConfigure;
import org.apache.sis.services.csw.RecordResult;
import org.apache.sis.services.csw.reponse.GetRecordByIdReponse;
import org.apache.sis.services.csw.reponse.GetRecordsReponse;
import org.apache.sis.services.csw.request.AbstractRecord;
import org.apache.sis.services.csw.request.AnyText;
import org.apache.sis.services.csw.request.BriefRecord;
import org.apache.sis.services.csw.request.Capabilities;

/**
 *
 * @author haonguyen
 */
@Path("/csw/2.0.2")
public class CSW {

    ConfigurationReader path = new ConfigurationReader();

    @GET
    @Path("/getcapabilities")
    @Produces(MediaType.APPLICATION_XML)
    public File getCapabilities(@QueryParam("service") String service, @QueryParam("version") String Version, @QueryParam("request") String request) throws URISyntaxException {
        if (request.equals("GetCapabilities")) {
            File file = new File(ConfigurationReader.class.getResource("Capabilities.xml").toURI()) ;
            return file;
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/describerecord")
    public File DescribeRecord(@QueryParam("service") String service, @QueryParam("version") String Version, @QueryParam("request") String request) throws ParseException, Exception {
        if (request.equals("DescribeRecord")) {
            File file = new File(ConfigurationReader.class.getResource("DescribeRecord.xsd").toURI()) ;
            return file;
        }
        return null;
    }

    @GET
    @Path("/getrecords")
    @Produces(MediaType.APPLICATION_XML)
    public GetRecordsReponse GetRecords(
            @QueryParam("service") String service,
            @QueryParam("version") String Version,
            @QueryParam("request") String request,
            @QueryParam("elementSetName") String elementset,
            @QueryParam("resultType") String resultType,
            @QueryParam("typeName") String typeName,
            @QueryParam("constraintLanguage") String constraintLanguage,
            @QueryParam("constraint") String constraint,
            @QueryParam("format") String format,
            @QueryParam("identifier") String identifier,
            @QueryParam("west") String west,
            @QueryParam("east") String east,
            @QueryParam("south") String south,
            @QueryParam("north") String north,
            @QueryParam("startDate") String date1,
            @QueryParam("rangeDate") String date2,
            @QueryParam("startPosition") int start,
            @QueryParam("maxRecords") int size) throws ParseException, Exception {
        
        //Chay BT
//        if (request.equals("GetRecords") && start >= 0 && size > 0) {
//            GetRecordsReponse reponse = new GetRecordsReponse();
//            RecordConfigure record = new RecordConfigure(path.getValue("Path"), Version, service);
//            reponse.getSearchstatus();
//            reponse.setSearchresults(record.getRecords(start, size,elementset));
//            return reponse;
//        }
        
        if(request.equals("GetRecords")) {
            AnyText t = new AnyText(path.getValue("Path"), Version, service, elementset, constraintLanguage, constraint, format, identifier, date1, date2);
            t.setBbox(west, east, south, north);
            t.filter();        
            
            for(int i = 0; i < t.getData().size(); i++) {
                System.out.println(t.getData().get(i).getIdentifier());
            }
            GetRecordsReponse response = new GetRecordsReponse();
            response.getSearchstatus();
            response.setSearchresults(t.getResult(start, size, elementset));
            //ok chua?
            return response;
        }
        
        return null;  
    }
    
    @GET
    @Path("/getrecordbyid")
    @Produces(MediaType.APPLICATION_XML)
    public GetRecordByIdReponse getRecordById(
            @QueryParam("service") String service,
            @QueryParam("version") String Version,
            @QueryParam("request") String request,
            @QueryParam("elementSetName") String elementset,
            @QueryParam("Id") String id) throws ParseException, Exception {
        if (request.equals("GetRecordById")) {
            RecordConfigure record = new RecordConfigure(path.getValue("Path"),Version,service);
            GetRecordByIdReponse a = record.getRecordById(id, elementset);
            return a;
        }
        return null;
    }

    @GET
    @Path("/download/{name}")
    @Produces("text/plain")
    public Response getFileGeotiff(@PathParam("name") String name) throws IOException {
        File file = null;
        if(name.startsWith("LC")) {
            file = new File(path.getValue("Path") + "/geotiff/" + name.substring(0, 21) + "/" + name);
        }
        
        if(name.startsWith("M")) {
            file = new File(path.getValue("Path") + "/modis/" + name.substring(0, 41) + "/" + name);
            if(name.endsWith(".jpg")) {
                file = new File(path.getValue("Path") + "/modis/" + name.substring(0, 41) + "/BROWSE." + name.substring(0, name.length() - 8) + ".1.jpg");
            }
            
        }
        
        if(name.endsWith(".tar.gz")) {
            file = new File(path.getValue("Path") + "/package/" + name);
        }
  
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=" + name);
        return response.build();
    }
}
