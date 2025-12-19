package com.swaggerpetstore.validator;

import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.BaseValidator;
import com.swaggerpetstore.dto.request.AddNewOrderReq;
import com.swaggerpetstore.dto.response.AddNewOrderResponse;
import com.swaggerpetstore.dto.response.DeleteOrderByIdResponse;
import com.swaggerpetstore.enums.StoreEnums;
import com.swaggerpetstore.utils.ApiCallDataMapper;
import org.testng.Assert;

public class StoreValidator extends BaseValidator {

    public StoreValidator(ApiCallData apiCallData){
        super(apiCallData);

    }

    public void validateAddOrder(ApiCallData apiCallData, ApiCallDataMapper callDataMapper){
        verifySuccessStatusCode();
        AddNewOrderReq request= callDataMapper.getRequestDto(apiCallData,AddNewOrderReq.class);
        AddNewOrderResponse response= callDataMapper.getResponseDto(apiCallData,AddNewOrderResponse.class);
        Assert.assertEquals(response.getId(),request.getId(),"Order Id mismatch");
        Assert.assertEquals(response.getComplete(), request.getComplete(),"Order completed status wrong");
    }

    public void validateGetOrder(ApiCallData apiCallData, ApiCallDataMapper callDataMapper,long orderId){
        verifySuccessStatusCode();
        AddNewOrderResponse response= callDataMapper.getResponseDto(apiCallData,AddNewOrderResponse.class);
        Assert.assertEquals(response.getId(),orderId,"Order Id mismatch");
        Assert.assertEquals(response.getComplete(), StoreEnums.Complete.TRUE.isFlag(),"Order completed status wrong");
        Assert.assertEquals(response.getStatus(),StoreEnums.Status.PLACED.getValue(),"Order status mismatch");
    }

    public void validateDeleteOrder(ApiCallData apiCallData,ApiCallDataMapper callDataMapper,long orderID){
        verifySuccessStatusCode();
        DeleteOrderByIdResponse response= callDataMapper.getResponseDto(apiCallData, DeleteOrderByIdResponse.class);
        Assert.assertEquals(response.getMessage(),String.valueOf(orderID),"Mismatch in order ID: "+response.getMessage());
        Assert.assertEquals(response.getCode(),200,"Mismatch in code after delete operation");
    }
}
