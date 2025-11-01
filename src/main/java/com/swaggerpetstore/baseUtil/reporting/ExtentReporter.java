package com.swaggerpetstore.baseUtil.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporter {

  private static ExtentReports extent;

  public static ExtentReports getInstance(String suiteName) {
    if (extent == null) {
      extent = createInstance(suiteName);
    }
    return extent;
  }

  private static ExtentReports createInstance(String suiteName) {
    String timestamp =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
    String reportPath =
        System.getProperty("user.dir")
            + "/test-output/"
            + suiteName
            + "_Report_"
            + timestamp
            + ".html";

    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
    spark.config().setTheme(Theme.DARK);
    spark.config().setEncoding("utf-8");
    spark.config().setReportName(suiteName);
    spark.config().setDocumentTitle("TestNG Report");

    ExtentReports extent = new ExtentReports();
    extent.attachReporter(spark);
    return extent;
  }
}
