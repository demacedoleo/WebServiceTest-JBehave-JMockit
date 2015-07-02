package com.webservice.domain;

import lombok.Data;

@Data
public class CurrentWeather {
	String location;
	String time;
	String wind;
	String visibility;
	String skyConditions;
	String temperature;
	String dewPoint;
	String relativeHumidity;
	String pressure;
	String status;
}
