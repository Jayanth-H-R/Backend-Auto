package com.swaggerpetstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swaggerpetstore.baseUtil.ApiCallData;

public class ApiCallDataMapper {
	/**
	 * This class is used to map the ApiCallData to the required DTOs. It uses
	 * Jackson ObjectMapper to convert the JSON response/request body to the
	 * required DTO class.
	 */
	public <T> T getResponseDto(ApiCallData apiCallData, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		T requiredDto = null;
		try {
			requiredDto = objectMapper.readValue(apiCallData.getResponse().getBody().asString(), clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return requiredDto;
	}

	public <T> T getRequestDto(ApiCallData apiCallData, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		T requiredDto = null;
		try {
			requiredDto = objectMapper.readValue(apiCallData.getRequest().getBody().toString(), clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return requiredDto;
	}
}
