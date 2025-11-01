package com.swaggerpetstore.baseUtil;


import com.swaggerpetstore.baseUtil.logger.ILogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurlLoggerInterceptor implements HttpRequestInterceptor, ILogger {
  private static final Logger log = Logger.getLogger(CurlLoggerInterceptor.class.getName());
  @Override
  public void process(HttpRequest request, HttpContext context) {
    StringBuilder curl = new StringBuilder("curl");

    // Add method (only if not GET, since curl defaults to GET)
    String method = request.getRequestLine().getMethod();
    if (!"GET".equalsIgnoreCase(method)) {
      curl.append(" -X ").append(method);
    }

    // Extract host
    String host = "";
    for (var header : request.getAllHeaders()) {
      if ("Host".equalsIgnoreCase(header.getName())) {
        host = header.getValue();
        break;
      }
    }

    // Full URL
    String fullUrl = "https://" + host + request.getRequestLine().getUri();
    curl.append(" --location '").append(fullUrl).append("' \\");

    // Headers
    for (var header : request.getAllHeaders()) {
      curl.append("\n--header '")
          .append(header.getName())
          .append(": ")
          .append(header.getValue())
          .append("' \\");
    }

    // Body (if present)
    if (request instanceof HttpEntityEnclosingRequest) {
      HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest) request;
      HttpEntity entity = entityRequest.getEntity();

      if (entity != null) {
        try {
          String body = EntityUtils.toString(entity);
          // Escape single quotes
          body = body.replace("'", "'\"'\"'");
          curl.append("\n--data '").append(body).append("' \\");
        } catch (IOException e) {
          log.log(Level.WARNING, "Failed to read request body", e);
        }
      }
    }

    // Remove trailing backslash
    if (curl.toString().endsWith(" \\")) {
      curl.setLength(curl.length() - 2);
    }

    log.info(String.format("\n\nCurl ==> \n%s\n", curl));
    loggerExtent.info("Curl Request ==> " + curl);
  }
}
