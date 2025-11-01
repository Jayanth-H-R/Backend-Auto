package com.swaggerpetstore.baseUtil;

import org.testng.Assert;

public class BaseValidator {

    private ApiCallData apiCallData;

    public BaseValidator(ApiCallData apiCallData) {
        this.apiCallData = apiCallData;
    }

    public void verifySuccessStatusCode() {
        Assert.assertTrue(
            this.apiCallData.getResponse().statusCode() == 200, "Success Status code mismatch");
    }

    public void verifyBadRequestStatusCode() {
        Assert.assertTrue(
            this.apiCallData.getResponse().statusCode() == 400, "Bad request status code mismatch");
    }
}
