package com.swaggerpetstore.apiClient;

import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.BaseApiClient;
import com.swaggerpetstore.config.ApiResources;
import com.swaggerpetstore.dto.request.AddNewOrderReq;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import java.io.File;

public class StoreApiClient extends BaseApiClient {

	public StoreApiClient() {
		super();
	}

	public ApiCallData addOrder(AddNewOrderReq addNewOrderReq) {
		FilterableRequestSpecification filterableRequestSpecification = getRequestSpecification(ContentType.JSON);
		filterableRequestSpecification.baseUri(ApiResources.PET_BASE_URL + "/store/order");
		filterableRequestSpecification.body(addNewOrderReq);
		Response response = filterableRequestSpecification.post();
		return new ApiCallData(filterableRequestSpecification, response, addNewOrderReq);
	}

	public ApiCallData getOrderByID(long orderID) {
		FilterableRequestSpecification requestSpecification = getRequestSpecification(ContentType.JSON);
		requestSpecification.baseUri(ApiResources.PET_BASE_URL + "/store/order/" + orderID);
		Response response = requestSpecification.get();
		return new ApiCallData(requestSpecification, response, null);
	}

    public ApiCallData deleteOrderById(long orderID){
        FilterableRequestSpecification requestSpecification= getRequestSpecification(ContentType.JSON);
        requestSpecification.baseUri(ApiResources.PET_BASE_URL+"/store/order/"+orderID);
        Response response = requestSpecification.delete();
        return new ApiCallData(requestSpecification, response, null);

    }

}
