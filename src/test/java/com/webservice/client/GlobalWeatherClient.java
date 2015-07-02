package com.webservice.client;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.webservice.utils.ClientPortConfigurator;

/**
 * @since 24-05-2015
 * @author leonardo.pereira
 * Global Weather Client
 */

@Data
@Slf4j
public class GlobalWeatherClient {
	
	private Resource resource;
	private GlobalWeatherSoap port; 

	@Value("${com.webservice.globalweather.endpoint}")
	private String balancerAddress;
	
	@Value("${com.webservice.globalweather.port}")
	private String balancerPort;
	
	/**
	 * Init Service
	 */
	@PostConstruct
	public void initService() {
		try {
			String namespaceURI = "http://www.webserviceX.NET";
			String localPart = "GlobalWeather";
			QName features = new QName(namespaceURI , localPart);
			GlobalWeather globalWeather = new GlobalWeather(resource.getURL(), features);
			port = (GlobalWeatherSoap) ClientPortConfigurator.configurePort((BindingProvider) 
					globalWeather.getGlobalWeatherSoap(), balancerAddress, balancerPort); 
			
		} catch (IOException io) {
			log.error("action=\"Global Weather\", description=\"Error initializing GlobalWeatherClient in initService\", "
					+ "error=\"{}\"", io.getLocalizedMessage(), io);
		}
	}
	
	/**
	 * Get Weather By City and Country Name
	 * @param country
	 * @param city
	 * @return String
	 */
	public String getWeather(String country, String city) {
		String weather = null;
		try {
			weather = port.getWeather(city, country);

		}catch(WebServiceException wex) {
			log.error("action=\"Global Weather\", description=\"Error GlobalWeatherClient Request\", "
					+ "error=\"{}\"", wex.getLocalizedMessage(), wex);
		} 
		return weather; 
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
