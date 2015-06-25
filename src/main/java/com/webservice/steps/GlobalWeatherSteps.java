package com.webservice.steps;

import javax.xml.bind.JAXBException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webservice.client.GlobalWeatherClient;
import com.webservice.utils.WebServiceUtils;

/**
 * @since 25-05-2015
 * @author leonardo.pereira
 * Global Weather Service Steps
 */

@Component
public class GlobalWeatherSteps {   
	
	private String currentWeather;
	private String response;
	private String country;
	private String city;
	
	@Autowired private GlobalWeatherClient weatherClient;
	
	@Autowired private WebServiceUtils webServiceUtils;
	
	/**
	 * Prepare data between steps
	 * @param countryName
	 * @param cityName
	 */
	@Given("A country $countryName and city $cityName")
	public void doPrepareData(@Named("countryName") String countryName, @Named("cityName") String cityName) {
		country = countryName;
		city = cityName;
	}

	/**
	 * Call service
	 * @throws JAXBException
	 */
	@When("I consume GetWeather service")
	public void doCallService() throws JAXBException {
		response = weatherClient.getWeather(country, city);
		currentWeather = webServiceUtils.findTagValue(response, "Temperature"); 
	}

	/**
	 * Verify results
	 * @param temperture
	 */
	@Then("Verify that temperature is $temperture")
	public void verifyTemperature(@Named("temperture") String temperture) {
		Assert.assertTrue(currentWeather.contains("C"));
	}

}
