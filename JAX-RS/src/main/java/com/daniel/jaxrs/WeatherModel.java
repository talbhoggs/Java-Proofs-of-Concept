package com.daniel.jaxrs;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class WeatherModel {

	private Integer temperature;
	private String humidity;
	private Boolean rain;
	private String zipCode;

	public WeatherModel() {

	}

	public WeatherModel(Integer temperature, String humidity, Boolean rain,
			String zipCode) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.rain = rain;
		this.zipCode = zipCode;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public Boolean getRain() {
		return rain;
	}

	public void setRain(Boolean rain) {
		this.rain = rain;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
