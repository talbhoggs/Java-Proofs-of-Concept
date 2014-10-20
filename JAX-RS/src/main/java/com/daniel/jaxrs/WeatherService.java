package com.daniel.jaxrs;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/WeatherService")
public class WeatherService {

	public static HashMap<String, WeatherModel> weatherData = new  HashMap<String, WeatherModel>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Rain")
	public String isRain() {
		return "{ rain: 1 }";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Temperature/{zipcode}")
	public String temperatureByZip(@PathParam("zipcode") Integer zipCode) {
		if (zipCode < 33333)
			return "{ temperature: 25 }";
		else {
			return "{ temparature: 18 }";
		}
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String submitWeatherForm(@FormParam("temperature") Integer temperature, @FormParam("humidity") String humidity, 
			@FormParam("rain") Boolean rain, @FormParam("zipCode") String zipCode) {
		
		WeatherModel wm = new WeatherModel(temperature, humidity, rain, zipCode);
		weatherData.put(zipCode, wm);
		
		return "Ok: " + weatherData.size();
		
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Path("WeatherData/{zipcode}")
	public WeatherModel weatherDataByZip(@PathParam("zipcode") String zipCode) {
		WeatherModel wm = weatherData.get(zipCode);
		if(wm == null) {
			wm = new WeatherModel(0, "0", false, zipCode);
		}
		return wm;
	}

}