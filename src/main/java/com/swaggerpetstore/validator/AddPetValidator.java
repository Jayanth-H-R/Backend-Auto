package com.swaggerpetstore.validator;

import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.BaseValidator;
import com.swaggerpetstore.dto.request.AddNewPetRequest;
import com.swaggerpetstore.dto.request.GetPetByIdRequest;
import com.swaggerpetstore.dto.response.AddNewPetResponse;
import com.swaggerpetstore.dto.testDataDto.AddNewPetData;
import com.swaggerpetstore.utils.ApiCallDataMapper;
import org.testng.Assert;

public class AddPetValidator extends BaseValidator {
/**
 *  This class is responsible for validating the response of the Add New Pet API call
    It extends the BaseValidator class to utilize common validation methods.
    The validateAddPet method checks if the API call was successful and maps the request and response DTOs.
 */
    public AddPetValidator(ApiCallData apiCallData) {
        super(apiCallData);
    }
    public void validateAddPet(ApiCallData apiCallData, ApiCallDataMapper apiCallDataMapper) {
        //
        verifySuccessStatusCode();
        AddNewPetRequest request= apiCallDataMapper.getRequestDto(apiCallData, AddNewPetRequest.class);
        AddNewPetResponse response= apiCallDataMapper.getResponseDto(apiCallData, AddNewPetResponse.class);
        Assert.assertEquals(response.getId(), request.getId(), "Pet ID mismatch");
        Assert.assertEquals(response.getName(), request.getName(), "Pet Name mismatch");
        Assert.assertEquals(response.getCategory().getId(), request.getCategory().getId(), "Pet Category ID mismatch");
    }

    public void validateGetPetById(ApiCallData apiCallData, ApiCallDataMapper apiCallDataMapper) {
        // This method validates the response of the Get Pet By ID API call
        verifySuccessStatusCode();
        AddNewPetResponse response = apiCallDataMapper.getResponseDto(apiCallData, AddNewPetResponse.class);
        //GetPetByIdRequest request = apiCallDataMapper.getRequestDto(apiCallData, GetPetByIdRequest.class);
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getId(),response.getId() , "Pet ID mismatch");
    }

    public void validateUpdatePet(ApiCallData apiCallData, ApiCallDataMapper apiCallDataMapper) {
        // This method validates the response of the Update Pet API call
        verifySuccessStatusCode();
        AddNewPetResponse response = apiCallDataMapper.getResponseDto(apiCallData, AddNewPetResponse.class);
        AddNewPetRequest request = apiCallDataMapper.getRequestDto(apiCallData, AddNewPetRequest.class);
        Assert.assertEquals(response.getId(), request.getId(), "Updated Pet ID mismatch");
        Assert.assertEquals(response.getName(), request.getName(), "Updated Pet Name mismatch");
        Assert.assertEquals(response.getCategory().getId(), request.getCategory().getId(), "Updated Pet Category ID mismatch");
    }

    public void validateDeletePet(ApiCallData apiCallData, ApiCallDataMapper apiCallDataMapper) {
        // This method validates the response of the Delete Pet API call
        verifySuccessStatusCode();

    }


}
