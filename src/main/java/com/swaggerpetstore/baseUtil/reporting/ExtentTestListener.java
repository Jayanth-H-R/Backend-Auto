package com.swaggerpetstore.baseUtil.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtentTestListener implements ITestListener, ISuiteListener, IConfigurationListener {

  private static final Logger log = LogManager.getLogger(ExtentTestListener.class);
  private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
  private static final ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();
  private static final Map<String, ExtentTest> parentTestMap = new ConcurrentHashMap<>();
  private static final Map<ITestNGMethod, AtomicInteger> testMethodInvocationCount =
      new ConcurrentHashMap<>();
  private static final ThreadLocal<String> suiteName = new ThreadLocal<>();
  private static final ThreadLocal<Boolean> configFailure = ThreadLocal.withInitial(() -> false);
  private static ExtentReports extent;

  private static final AtomicInteger testCaseCount =
      new AtomicInteger(0); // Total count of all test cases
  private static final AtomicInteger skippedCount = new AtomicInteger(0); // Count of skipped tests
  private static final AtomicInteger failTestCaseCount = new AtomicInteger(0); // Track failed tests
  private static final AtomicInteger passCount = new AtomicInteger(0); // Track passed tests

  public static ExtentTest getInstance() {
    return childTest.get();
  }

  @Override
  public synchronized void onStart(ISuite suite) {
    suiteName.set(suite.getName());
    extent = ExtentReporter.getInstance(suiteName.get());
  }

  @Override
  public synchronized void onFinish(ISuite suite) {
    // Log the pass/fail/skipped counts at the end of the suite
    int totalExecuted = passCount.get() + skippedCount.get() + failTestCaseCount.get();
    log.info("Test suite finished: " + suiteName.get());
    log.info(
        "Total Test Cases: "
            + testCaseCount.get()
            + ", Executed: "
            + totalExecuted
            + ", Passed: "
            + passCount.get()
            + ", Skipped: "
            + skippedCount.get()
            + ", Failed: "
            + failTestCaseCount.get());

    if (extent != null) {
      extent.flush();
    }
  }

  @Override
  public synchronized void onTestStart(ITestResult result) {
    // Increment total test count for every test
    testCaseCount.incrementAndGet();

    String methodName = result.getMethod().getMethodName();
    String parentName = result.getTestClass().getRealClass().getSimpleName() + " : " + methodName;

    // Create parent only once per method
    ExtentTest parent =
        parentTestMap.computeIfAbsent(
            parentName,
            name -> {
              ExtentTest test = extent.createTest(name);
              test.assignCategory(result.getTestClass().getName());
              return test;
            });

    parentTest.set(parent);

    // Create child with extracted parameter name
    String childName = getDataSetName(result);
    ExtentTest child = parent.createNode(childName);
    childTest.set(child);

    child.info("Starting execution of : " + result.getName());
    log.info("Starting: " + result.getName());
  }

  @Override
  public synchronized void onTestSuccess(ITestResult result) {
    // Increment pass count
    passCount.incrementAndGet();

    // Mark test as passed with a label
    String methodName = result.getMethod().getMethodName();
    String dataSetName = getDataSetName(result);
    childTest
        .get()
        .pass(
            MarkupHelper.createLabel(
                "Test Passed - " + methodName + " (" + dataSetName + ")", ExtentColor.GREEN));
  }

  @Override
  public synchronized void onTestFailure(ITestResult result) {
    // Increment failure count
    failTestCaseCount.incrementAndGet();

    // Mark test as failed with a label
    String methodName = result.getMethod().getMethodName();
    String dataSetName = getDataSetName(result);
    childTest
        .get()
        .fail(
            MarkupHelper.createLabel(
                "Test Failed - " + methodName + " (" + dataSetName + ")", ExtentColor.RED));

    // Log the exception/stack trace if available
    childTest.get().fail(result.getThrowable());
  }

  @Override
  public synchronized void onTestSkipped(ITestResult result) {
    ExtentTest test = childTest.get();
    if (test == null && parentTest.get() != null) {
      test = parentTest.get();
    }

    String labelText;
    ExtentColor color;

    if (configFailure.get()) {
      labelText = "Skipped due to configuration failure: " + result.getMethod().getMethodName();
      color = ExtentColor.YELLOW;

      if (parentTest.get() != null) {
        parentTest.get().skip(MarkupHelper.createLabel(labelText, color));
        parentTest.get().info("Configuration-related skip.");
      }
    } else {
      labelText = "Test Skipped: " + result.getMethod().getMethodName();
      color = ExtentColor.ORANGE;

      if (test != null) {
        test.skip(MarkupHelper.createLabel(labelText, color));
        test.skip(result.getThrowable());
      }
    }

    skippedCount.incrementAndGet();
    configFailure.set(false); // Reset for next test
  }

  // Helper: Extract a meaningful
  private String getDataSetName(ITestResult result) {
    ITestNGMethod method = result.getMethod();

    // Get or initialize the counter for this method
    AtomicInteger counter =
        testMethodInvocationCount.computeIfAbsent(method, m -> new AtomicInteger(0));

    int invocationIndex = counter.incrementAndGet(); // Test_1, Test_2, etc.

    return "Test_" + invocationIndex;
  }

  @Override
  public void onConfigurationFailure(ITestResult itr) {
    configFailure.set(true);
    log.warn("Configuration method FAILED: " + itr.getMethod().getMethodName());
  }

  @Override
  public void onConfigurationSkip(ITestResult itr) {
    configFailure.set(true);
    log.warn("Configuration method SKIPPED: " + itr.getMethod().getMethodName());
  }

  @Override
  public void onConfigurationSuccess(ITestResult itr) {
    configFailure.set(false); // Reset config failure on success
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

  @Override
  public void onTestFailedWithTimeout(ITestResult result) {
    onTestFailure(result); // Reuse existing failure logic
    log.error("Test failed due to timeout: " + result.getName());
  }
}
