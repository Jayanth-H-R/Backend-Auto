package com.swaggerpetstore.baseUtil.logger;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.swaggerpetstore.baseUtil.JSONUtils;
import com.swaggerpetstore.baseUtil.reporting.ExtentTestListener;
import org.apache.logging.log4j.LogManager;

public class Logger {
	public static org.apache.logging.log4j.Logger Log;

	public Logger() {
		Log = LogManager.getLogger(Logger.class);
	}

	public void jsonInfo(String heading, String body) {
		Log.info(body);

		if (ExtentTestListener.getInstance() != null) {
			// Check if the body is a valid JSON string
			if (JSONUtils.isJSONValid(body)) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					Object jsonObject = mapper.readValue(body, Object.class);
					String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
					// Create HTML for displaying the JSON in a collapsible format
					String html = "<details style='margin-bottom: 12px;'>" + "<summary style='" + "font-weight: bold; "
							+ "background-color: #3a3f44; " // Darker blue/gray
							+ "color: #e9ecef; " // Light text
							+ "padding: 6px 12px; " + "border-radius: 6px; " + "border: 1px solid #495057; "
							+ "cursor: pointer; " + "user-select: none;" + "'>" + heading + "</summary>"
							+ "<pre style='" + "background-color: #2d333b; " // Dark background
							+ "color: #adb5bd; " // Light gray text
							+ "padding: 12px; " + "border-radius: 4px; " + "border: 1px solid #444c56; "
							+ "white-space: pre-wrap; " + "font-family: \"Courier New\", monospace; "
							+ "margin-top: 8px; " + "max-height: 300px; " + "overflow-y: auto;" + "'>" + prettyJson
							+ "</pre>" + "</details>";

					ExtentTestListener.getInstance().log(Status.INFO, html);

				} catch (Exception e) {
					Log.error("Failed to parse JSON: ", e);
					ExtentTestListener.getInstance().log(Status.INFO, heading + " : " + body);
				}
			} else {
				ExtentTestListener.getInstance().log(Status.INFO, heading + " : " + body);
			}
		}
	}

	public void info(String text) {
		Log.info(text);
		if (ExtentTestListener.getInstance() != null) {
			ExtentTestListener.getInstance().log(Status.INFO, text);
		}
	}

	public void error(String s) {
		Log.error(s);
		if (ExtentTestListener.getInstance() != null) {
			ExtentTestListener.getInstance().log(Status.WARNING, s);
		}
	}

}
