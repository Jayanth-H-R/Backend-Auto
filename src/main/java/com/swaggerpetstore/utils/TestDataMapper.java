package com.swaggerpetstore.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestDataMapper {

  public <T> T getJsonDataNew(String fileName, String env, Class<T> clazz) {
    T dto = null;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String baseDirectory =
          "src"
              + File.separator
              + "test"
              + File.separator
              + "resources"
              + File.separator
              + "testData";
      String filePath = baseDirectory + "/" + fileName;

      File file = new File(filePath);
      JsonNode rootNode = objectMapper.readTree(file);
      JsonNode environmentNode = rootNode.path(env);

      if (environmentNode.isMissingNode() || environmentNode.isEmpty()) {
        throw new IOException("No data found for environment: " + env);
      }
      dto = objectMapper.treeToValue(environmentNode, clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }
}
