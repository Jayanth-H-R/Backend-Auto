package com.swaggerpetstore.controller;

import com.swaggerpetstore.apiClient.PetApiClient;
import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.logger.ILogger;
import com.swaggerpetstore.dto.request.AddNewPetRequest;
import com.swaggerpetstore.dto.testDataDto.AddNewPetData;
import com.swaggerpetstore.services.AddPetRequestHelper;
import org.apache.logging.log4j.LogManager;

public class PetController implements ILogger {
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(PetController.class);
	private AddPetRequestHelper addPetRequestHelper;
	private PetApiClient petApiClient;
	public PetController() {
		addPetRequestHelper = new AddPetRequestHelper();
		petApiClient = new PetApiClient();

	}

	public ApiCallData addNewPet(AddNewPetData addNewPetdata) {
		log.info("==> Calling Add New Pet API <==");
		loggerExtent.info("==> Calling Add New Pet API <==");
		AddNewPetRequest request = addPetRequestHelper.createAddNewPetRequest(addNewPetdata);
		return petApiClient.addPet(request);
	}
	public ApiCallData getPetById(int petId) {
		log.info("==> Calling Get Pet By ID API <==");
		loggerExtent.info("==> Calling Get Pet By ID API <==");
		return petApiClient.getPetById(petId);
	}

	public ApiCallData updatePet(int petId, AddNewPetData addNewPetData) {
		log.info("==> Calling Update Pet API <==");
		loggerExtent.info("==> Calling Update Pet API <==");
		AddNewPetRequest request = addPetRequestHelper.updatePetRequest(addNewPetData);
		return petApiClient.updatePet(request);
	}

	public ApiCallData deletePet(int petId) {
		log.info("==> Calling Delete Pet API <==");
		loggerExtent.info("==> Calling Delete Pet API <==");
		return petApiClient.deletePet(petId);
	}
}
