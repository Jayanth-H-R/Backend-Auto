package com.swaggerpetstore.apiClient;

import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.BaseApiClient;

import com.swaggerpetstore.config.ApiResources;
import com.swaggerpetstore.dto.request.AddNewPetRequest;
import com.swaggerpetstore.dto.testDataDto.AddNewPetData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;



public class PetApiClient extends BaseApiClient {



    public PetApiClient() {
        super();
    }


    public ApiCallData addPet(AddNewPetRequest addNewPetRequest){
        FilterableRequestSpecification filterableRequestSpecification = getRequestSpecification(ContentType.JSON);
        filterableRequestSpecification.baseUri(ApiResources.PET_BASE_URL+ "/pet");
        filterableRequestSpecification.body(getBody(addNewPetRequest));

        Response response = filterableRequestSpecification.post();
        return new ApiCallData(filterableRequestSpecification, response, addNewPetRequest);
    }

    public ApiCallData getPetById(int petId) {
        FilterableRequestSpecification filterableRequestSpecification = getRequestSpecification(ContentType.JSON);

        filterableRequestSpecification.baseUri(ApiResources.PET_BASE_URL + "/pet/" + petId);

        Response response = filterableRequestSpecification.get();
        return new ApiCallData(filterableRequestSpecification, response, null);
    }

    public ApiCallData updatePet( AddNewPetRequest addNewPetRequest) {
        FilterableRequestSpecification filterableRequestSpecification = getRequestSpecification(ContentType.JSON);

        filterableRequestSpecification.baseUri(ApiResources.PET_BASE_URL + "/pet");
        filterableRequestSpecification.body(getBody(addNewPetRequest));

        Response response = filterableRequestSpecification.put();
        return new ApiCallData(filterableRequestSpecification, response, addNewPetRequest);
    }

    public ApiCallData deletePet(int petId) {
        FilterableRequestSpecification filterableRequestSpecification = getRequestSpecification(ContentType.JSON);

        filterableRequestSpecification.baseUri(ApiResources.PET_BASE_URL + "/pet/" + petId);

        Response response = filterableRequestSpecification.delete();
        return new ApiCallData(filterableRequestSpecification, response, null);
    }

    // Additional methods for interacting with the Pet API can be added here
}
