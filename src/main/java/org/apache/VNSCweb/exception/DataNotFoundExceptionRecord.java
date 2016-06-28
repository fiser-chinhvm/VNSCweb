/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.VNSCweb.model.ErrorMessage;

/**
 *
 * @author haonguyen
 */
@Provider
public class DataNotFoundExceptionRecord implements ExceptionMapper<DataNotFoundException>{
    @Override
    public Response toResponse(DataNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://VNSCweb.apache.org");
        return Response.status(Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
