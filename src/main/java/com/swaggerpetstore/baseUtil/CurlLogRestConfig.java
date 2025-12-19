package com.swaggerpetstore.baseUtil;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.impl.client.DefaultHttpClient;

public class CurlLogRestConfig {

	/** Returns a RestAssuredConfig object with a cURL logging interceptor added. */
	public static RestAssuredConfig createConfig() {
		return RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().httpClientFactory(() -> {
			// Create the HTTP client manually
			DefaultHttpClient client = new DefaultHttpClient();
			client.addRequestInterceptor(new CurlLoggerInterceptor());
			return client;
		}));
	}
}
