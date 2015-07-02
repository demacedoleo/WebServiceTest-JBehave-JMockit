package com.webservice.mocks;

import mockit.Mock;
import mockit.MockUp;
import net.webservicex.GlobalWeatherSoap;

import com.webservice.client.GlobalWeatherClient;

/**
 * @since 25-05-2015
 * @author leonardo.pereira Mocks Global Weather
 */

public class GlobalWeatherMocks {

	private static GlobalWeatherClient weatherClient;

	/**
	 * Initialize Mocks Global Weather
	 */
	public static void initMocks(GlobalWeatherClient client) {
		weatherClient = client;
		mockGetWeather();
	}

	/**
	 * Mock Get Weather
	 */
	private static void mockGetWeather() {
		final GlobalWeatherSoap port = new MockUp<GlobalWeatherSoap>() {

			@Mock
			public String getWeather(String cityName, String countryName) {
				return "40 C";
			}

			@Mock
			public String getCitiesByCountry(String countryName) {
				return null;
			}
		}.getMockInstance();

		weatherClient.setPort(port);
	}
}
