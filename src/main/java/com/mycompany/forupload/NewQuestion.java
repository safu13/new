/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.forupload;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author shafat
 */
@Path("qa")
public class NewQuestion {

    @Context
    private UriInfo context;

    /**
     * 
    public NewQuestion() {
    }

    /**
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{name}")
    @Produces("application/json")
    public String getJson(@PathParam("name") String name) {
        //TODO return proper representation object
        String answer = "Your majesty! Jon Snow knows nothing! So do I!";        
        
        
        return new Gson().toJson( answer );
    }

    
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
