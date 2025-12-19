package com.swaggerpetstore.services;

import com.github.javafaker.Faker;
import com.swaggerpetstore.dto.request.AddNewOrderReq;
import com.swaggerpetstore.dto.testDataDto.AddNewOrderData;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StoreRequestHelper {

	public AddNewOrderReq createAddNewOrderReq(AddNewOrderData orderData,long orderId) {
		ZonedDateTime zdt = ZonedDateTime.of(2025, 11, 21, 9, 24, 9, 752_000_000, // 752 ms = 752,000,000 ns
				ZoneOffset.UTC);

		String timestamp = zdt.format(DateTimeFormatter.ISO_INSTANT);
		return AddNewOrderReq.builder().id(orderId).petId(orderData.getPetId()).quantity(3).status("placed")
				.shipDate(timestamp).complete(true).build();
	}
}
