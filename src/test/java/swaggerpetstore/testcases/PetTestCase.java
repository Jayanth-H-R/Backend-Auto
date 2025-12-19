package swaggerpetstore.testcases;

import com.github.javafaker.Faker;
import com.swaggerpetstore.baseUtil.ApiCallData;
import com.swaggerpetstore.baseUtil.logger.ILogger;
import com.swaggerpetstore.controller.PetController;
import com.swaggerpetstore.controller.StoreController;
import com.swaggerpetstore.dto.testDataDto.AddNewOrderData;
import com.swaggerpetstore.dto.testDataDto.AddNewPetData;
import com.swaggerpetstore.utils.ApiCallDataMapper;
import com.swaggerpetstore.utils.TestDataMapper;
import com.swaggerpetstore.validator.AddPetValidator;
import com.swaggerpetstore.validator.StoreValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//@Listeners(ExtentTestListener.class)
public class PetTestCase implements ILogger {

	public static final String env = "qa";

	private PetController petController;
	private AddNewPetData addNewPetData;
	private ApiCallDataMapper apiCallDataMapper;
    private StoreController storeController;
    private AddNewOrderData addNewOrderData;
    private long orderID;
    Faker fakerData = new Faker();
	/**
	 * This method initializes the test data and the controller before running the
	 * tests.
	 */
	@BeforeTest
	public void initiateData() {
		loggerExtent.info("==> Initializing swaggerpetstore.testcases.PetTestCase <==");
		loggerExtent.info("==> Reading test data and converting it to test data dto <==");
		// Initialize the data and controller
		addNewPetData = new TestDataMapper().getJsonDataNew("AddNewPet.json", env, AddNewPetData.class);
		petController = new PetController();
		apiCallDataMapper = new ApiCallDataMapper();
        storeController=new StoreController();
        addNewOrderData= new TestDataMapper().getJsonDataNew("PlaceOrderPet.json",env, AddNewOrderData.class);
	}

	/**
	 * This method adds a delay before each test to ensure that the environment is
	 * ready. It can be useful in scenarios where the API might take some time to be
	 * ready for the next test.
	 */
	@BeforeMethod
	public void delayBeforeTest() {
		try {
			Thread.sleep(2500); // Delay for 2 seconds before each test
		} catch (InterruptedException e) {
			loggerExtent.error("Error during delay before test: " + e.getMessage());
		}
	}

	@Test(description = "Test to add a new pet", priority = 0)
	public void testAddPet() {
		// Example of how to use the controller to add a pet
		loggerExtent.info("environment: " + env);
		loggerExtent.info("==> Starting test to add a new pet <==");
		ApiCallData apiCallData = petController.addNewPet(addNewPetData);
		loggerExtent.info("==> Validating the response for adding a new pet <==");
		new AddPetValidator(apiCallData).validateAddPet(apiCallData, apiCallDataMapper);
		loggerExtent.info("==> Finished test to add a new pet <==");

	}

	@Test(description = "Test to get a pet by ID", priority = 1)
	public void testGetPetById() {
		// Example of how to use the controller to get a pet by ID
		loggerExtent.info("==> Starting test to get a pet by ID <==");
		System.out.println("Pet ID from addNewPetData: " + addNewPetData.getId());
		ApiCallData apiCallData = petController.getPetById(addNewPetData.getId());
		loggerExtent.info("==> Validating the response for getting a pet by ID <==");
		new AddPetValidator(apiCallData).validateGetPetById(apiCallData, apiCallDataMapper);
		loggerExtent.info("==> Finished test to get a pet by ID <==");
	}

	@Test(description = "Test to update a pet by ID", priority = 2)
	public void testUpdatePetById() {
		// Example of how to use the controller to update a pet
		loggerExtent.info("==> Starting test to update a pet <==");
		ApiCallData apiCallData = petController.updatePet(addNewPetData.getId(), addNewPetData);
		loggerExtent.info("==> Validating the response for updating a pet <==");
		new AddPetValidator(apiCallData).validateUpdatePet(apiCallData, apiCallDataMapper);
		loggerExtent.info("==> Finished test to update a pet <==");
	}

	@Test(description = "Test to delete a pet by ID", priority = 3)
	public void testDeletePetById() {
		// Example of how to use the controller to delete a pet by ID
		loggerExtent.info("==> Starting test to delete a pet by ID <==");
		System.out.println("Pet ID from addNewPetData: " + addNewPetData.getId());
		ApiCallData apiCallData = petController.deletePet(addNewPetData.getId());
		loggerExtent.info("==> Validating the response for deleting a pet by ID <==");
		new AddPetValidator(apiCallData).validateDeletePet(apiCallData, apiCallDataMapper);
		loggerExtent.info("==> Finished test to delete a pet by ID <==");
	}

    @Test(description = "Test is to create new order in the store by providing necessary details", priority = 4)
    public void testAddOrder(){
        loggerExtent.info("==> Starting test to add new order to store <==");
        orderID = fakerData.number().hashCode();
        ApiCallData apiCallData = storeController.addNewOrder(addNewOrderData,orderID);
        loggerExtent.info("==> Validating the response for adding an order <==");
        new StoreValidator(apiCallData).validateAddOrder(apiCallData,apiCallDataMapper);
    }

    @Test(description = "Test to get a order details from store module by it's unique order id", priority = 5)
    public void testGetOrderById(){
        loggerExtent.info("==> Starting test to get order by id <==");
        ApiCallData apiCallData= storeController.getOrderById(orderID);
        loggerExtent.info("==> Validating the response for getting order by id <==");
        new StoreValidator(apiCallData).validateGetOrder(apiCallData,apiCallDataMapper,orderID);
    }

    @Test(description = "Test to delete an order from store module by it's unique order id", priority = 6)
    public void testDeleteOrderById(){
        loggerExtent.info("==> Starting test to delete order by id <==");
        ApiCallData apiCallData= storeController.deleteOrderById(orderID);
        loggerExtent.info("==> Starting test to delete order by id <==");
        new StoreValidator(apiCallData).validateDeleteOrder(apiCallData,apiCallDataMapper, orderID);
    }
}
