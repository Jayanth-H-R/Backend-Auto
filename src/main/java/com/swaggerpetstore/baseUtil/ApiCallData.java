package com.swaggerpetstore.baseUtil;
import com.swaggerpetstore.baseUtil.logger.ILogger;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class ApiCallData implements ILogger {
	private Response response;
	private FilterableRequestSpecification request;
	private Object requestObject;

	public ApiCallData(FilterableRequestSpecification request, Response response, Object requestObject) {
		this.response = response;
		this.request = request;
		this.requestObject = requestObject;
		this.extentLogging(request, response);
	}

	public Response getResponse() {
		return this.response;
	}

	public FilterableRequestSpecification getRequest() {
		return this.request;
	}

	public Object getRequestObject() {
		return this.requestObject;
	}

	public final void extentLogging(FilterableRequestSpecification request, Response response) {
		loggerExtent.info("Request URI => " + request.getURI());
		if (request.getBody() != null) {
			loggerExtent.jsonInfo("Request Body => ", request.getBody().toString());
		}

		loggerExtent.info("Response Code => " + response.getStatusCode());

		if (response.getBody() != null) {
			loggerExtent.jsonInfo("Response Body => ", response.getBody().asString());
		}
	}
}
