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
 * @author shaown
 */
@Path("greetings")
public class Welcome {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of welcomeKitty
     */
    public Welcome() {
    }

    /**
     * Retrieves representation of an instance of REST
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{name}")
    @Produces("application/json")
    public String getJson(@PathParam("name") String name) {
        //TODO return proper representation object
        String answer = "Hello, Kitty!";        
        name = name.toLowerCase();
        if(name.contains("how are"))
            answer += "I am fine";
        else if(name.contains("name"))
            answer += "My name is Md. Sharif Shadat Hossain";
        else
            answer += "Nice to meet you";
        
        return new Gson().toJson( answer );
    }

    /**
     * PUT method for updating or creating an instance of welcomeKitty
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
