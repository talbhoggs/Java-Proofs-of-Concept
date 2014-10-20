package com.daniel.jaxrs;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@javax.ws.rs.ApplicationPath("WeatherService")
@Path("Temperature/{zipcode}")
public class WeatherService2 extends javax.ws.rs.core.Application {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String temperatureByZip(@PathParam("zipcode") Integer zipCode) {
    	if(zipCode < 33333)
    		return "{ temperature: 25 }";
    	else {
    		return "{ temparature: 18 }";
    	}
    }

}
