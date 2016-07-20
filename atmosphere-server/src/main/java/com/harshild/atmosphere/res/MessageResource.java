package com.harshild.atmosphere.res;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.BroadcasterFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



@AtmosphereHandlerService(path = "/")
@Path("/")
public class MessageResource {

    @Suspend( contentType = MediaType.APPLICATION_JSON )
    @GET
    public String suspend(@Context final BroadcasterFactory factory){
        System.out.println("\n==============GET REQUEST================\n");
         return "";
    }


}

