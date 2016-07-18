package com.harshild.atmosphere.res;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.DefaultBroadcaster;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


import org.atmosphere.annotation.Asynchronous;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceFactory;
import org.atmosphere.cpr.*;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;



@AtmosphereHandlerService(path = "/{subscriptionid}",broadcaster = DefaultBroadcaster.class)
public class MessageResource {

    static final int TIMEOUT_MS = 50000;

    public static void start(){
        System.out.println("ATmosphere Resource registered==========================");
    }

    @Inject
    private BroadcasterFactory broadcasterFactory;

    @PathParam("subscriptionid")
    String subscriptionid;


    @Suspend( contentType = MediaType.APPLICATION_JSON ,period = TIMEOUT_MS)
    @GET
    public Broadcastable suspend(@Context final BroadcasterFactory factory){
        System.out.println("ATmosphere Resource registered==========================");
        System.out.println("===================\n=======================\n=======================\n========================\n==================================\n");

        return new Broadcastable( factory.lookup( subscriptionid, true ) );
    }


    @Context
    private HttpServletRequest request;

    @Path("/message")
    @Broadcast(writeEntity = false)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void broadcastNewMessage() throws IOException {
        Broadcaster broadcaster = broadcasterFactory.lookup( subscriptionid );
        if( broadcaster != null ) {
            broadcaster.broadcast(
                    new Response()
                            .setAuthor("NotificationServer")
                            .setMessage(request.getParameter("message"))
                            .setType(request.getParameter("type"))
                            .build()
                            .toJSONString());
        }
    }

    @Path("/unsubscribe")
    @Broadcast(writeEntity = false)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void unSubscribe(@Context final AtmosphereResource atmosphereResource) throws IOException {
        Broadcaster broadcaster = broadcasterFactory.lookup( subscriptionid );
        broadcaster.removeAtmosphereResource(atmosphereResource);
        broadcaster.getAtmosphereResources();
    }

}

