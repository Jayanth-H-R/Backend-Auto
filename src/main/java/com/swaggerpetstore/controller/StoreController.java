package com.swaggerpetstore.controller;

import com.swaggerpetstore.apiClient.StoreApiClient;
import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.logger.ILogger;
import com.swaggerpetstore.dto.request.AddNewOrderReq;
import com.swaggerpetstore.dto.testDataDto.AddNewOrderData;
import com.swaggerpetstore.services.StoreRequestHelper;
import org.apache.logging.log4j.LogManager;


public class StoreController implements ILogger {
    private static final org.apache.logging.log4j.Logger log= LogManager.getLogger(StoreController.class);
    private StoreRequestHelper storeRequestHelper;
    private StoreApiClient storeApiClient;

    public StoreController(){
        storeApiClient=new StoreApiClient();
        storeRequestHelper= new StoreRequestHelper();

    }
    public ApiCallData addNewOrder(AddNewOrderData addNewOrderData, long orderID){
        log.info("==> Calling add new order API <==");
        loggerExtent.info("==> Calling add new order API <==");
        AddNewOrderReq request=storeRequestHelper.createAddNewOrderReq(addNewOrderData,orderID);
        return storeApiClient.addOrder(request);
    }

    public ApiCallData getOrderById(long orderId){
        log.info("==> Calling get order API <==");
        loggerExtent.info("==> Calling get order API <==");
        return storeApiClient.getOrderByID(orderId);
    }

    public ApiCallData deleteOrderById(long orderID){
        log.info("==> Calling get order API <==");
        loggerExtent.info("==> Calling get order API <==");
        return storeApiClient.deleteOrderById(orderID);
    }
}
