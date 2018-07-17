/**
 * 
 */
package com.hilton.jsonComparator.test.config;

import java.io.IOException;

import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StandardTestConfig {
  private final ObjectMapper mapper = new ObjectMapper();
  private final ClassLoader classLoader = getClass().getClassLoader();

  protected JsonNode test1Expected;
  protected JsonNode test1Actual1;
  protected JsonNode test1Actual2;
  protected JsonNode test2Expected;
  protected JsonNode test2Actual1;
  protected JsonNode test3Expected;
  protected JsonNode test3Actual1;
  protected JsonNode test4Expected;
  protected JsonNode test4Actual1;
  // No jsons for test5
  protected JsonNode test6Expected;
  protected JsonNode test6Actual1;

  @Before
  public void getJsonTestFiles() throws JsonParseException, JsonMappingException, IOException {
    // test1
    test1Expected = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test1_expected.json"),
        JsonNode.class);
    test1Actual1 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test1_actual1.json"), JsonNode.class);
    test1Actual2 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test1_actual2.json"), JsonNode.class);

    // test2
    test2Expected = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test2_expected.json"), JsonNode.class);
    test2Actual1 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test2_actual1.json"), JsonNode.class);

    // test3
    test3Expected = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test3_expected.json"), JsonNode.class);
    test3Actual1 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test3_actual1.json"), JsonNode.class);

    // test4
    test4Expected = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test4_expected.json"), JsonNode.class);
    test4Actual1 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test4_actual1.json"), JsonNode.class);

    // test5 - No JSONS

    // test6
    test6Expected = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test6_expected.json"), JsonNode.class);
    test6Actual1 = mapper.readValue(
        classLoader.getResourceAsStream("jsons/" + "test6_actual1.json"), JsonNode.class);
  }

}
