package com.daniel.jaxrs;

import javax.ws.rs.core.*;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.*;

public class WeatherClient {

	public static void main(String[] args) {

		java.net.URI serviceURI = UriBuilder.fromUri(
				"http://localhost:8080/jaxrs").build();

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource weatherService = client.resource(serviceURI);

		String weatherResult = weatherService.path("Rest")
				.path("WeatherService").path("Rain")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		String weatherResult2 = weatherService.path("Rest")
				.path("WeatherService").path("Temperature/94043")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		String weatherResult3 = weatherService.path("Rest")
				.path("WeatherService").path("Temperature/12345")
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(weatherResult);
		System.out.println(weatherResult2);
		System.out.println(weatherResult3);

		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("temperature", "25");
		formData.add("humidity", "15%");
		formData.add("rain", "false");
		formData.add("zipCode", "94043");

		String response = weatherService.path("Rest").path("WeatherService")
				.type("application/x-www-form-urlencoded")
				.post(String.class, formData);

		System.out.println(response);

		String weatherResult4 = weatherService.path("Rest")
				.path("WeatherService").path("WeatherData/94043")
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(weatherResult4);

		String weatherResult5 = weatherService.path("Rest")
				.path("WeatherService").path("WeatherData/94043")
				.accept(MediaType.APPLICATION_XML).get(String.class);

		System.out.println(weatherResult5);

	}

}
