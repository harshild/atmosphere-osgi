package com.harshild.atmosphere;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.atmosphere.cpr.AtmosphereServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

import javax.servlet.Servlet;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by harshild on 7/18/2016.
 */
public class Activator extends DependencyActivatorBase{

    private ServiceRegistration registration;

    @Override
    public void init(BundleContext bundleContext, DependencyManager dependencyManager) throws Exception {

        Hashtable filterProps = new Hashtable();
        filterProps.put("alias", "/notification");
        filterProps.put("init.com.sun.jersey.config.property.packages", "com.harshild.atmosphere.res");
        filterProps.put("init.org.atmosphere.websocket.messageContentType", "application/json");
        filterProps.put("init.org.atmosphere.cpr.broadcaster.maxProcessingThreads", "10");

        this.registration = bundleContext.registerService(Servlet.class.getName(),new AtmosphereServlet(),filterProps);
    }
}