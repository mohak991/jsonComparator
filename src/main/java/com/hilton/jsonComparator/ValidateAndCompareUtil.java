package com.hilton.jsonComparator;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.JsonAssert.when;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.TREATING_NULL_AS_ABSENT;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.javacrumbs.jsonunit.core.Configuration;

public final class ValidateAndCompareUtil {
  
  public static void assertJsonAndValidate(JsonNode expected, JsonNode actual,
      Map<String, Object> fromMap, String... paths) throws IOException {

    String expectedJsonString = expected.toString();
    for (Map.Entry<String, Object> entry : fromMap.entrySet()) {
      expectedJsonString =
          expectedJsonString.replaceAll("\\{" + entry.getKey() + "\\}", (String) entry.getValue());
    }

    ObjectMapper mapper = new ObjectMapper();
    JsonNode originalNode = mapper.readTree(expectedJsonString);
    assertJSONEquals(originalNode, actual, paths);
  }

  public static void assertJSONEquals(JsonNode expected, JsonNode received,
      String... pathToIgnore) {

    Configuration configuration = when(TREATING_NULL_AS_ABSENT, IGNORING_ARRAY_ORDER);
    if (pathToIgnore != null && pathToIgnore.length > 0) {
      configuration = configuration.whenIgnoringPaths(pathToIgnore);
    }

    assertJsonEquals(expected, received, configuration);
  }

  public static void jsonAssertAndValidate(JsonNode expected, JsonNode actual, JsonNode fromJson)
      throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> fromMap = mapper.readValue(mapper.writeValueAsString(fromJson),
        new TypeReference<Map<String, Object>>() {
        });
    // jsonAssertAndValidate(expected, actual, null, fromJson, EMPTY_STRING);
  }

  public static void jsonAssertAndValidate(JsonNode expected, JsonNode actual, JsonNode fromJson,
      String... paths)
      throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> fromMap = mapper.readValue(mapper.writeValueAsString(fromJson),
        new TypeReference<Map<String, Object>>() {
        });

    // jsonAssertAndValidate(expected, actual, null, fromJson, paths);
  }
  
  public static void jsonAssertAndValidate(JsonNode expected, JsonNode actual,
      Map<String, Object> fromMap) {
    // jsonAssertAndValidate(expected, actual, fromMap, null, EMPTY_STRING);
  }

  public static void jsonAssertAndValidate(String expected, String actual,
      Map<String, Object> fromMap) {
    // jsonAssertAndValidate(expected.toString(), actual, fromMap, paths)
  }

  public static void jsonAssertAndValidate(String expected, String actual,
      Map<String, Object> fromMap, String... paths) {
    // jsonAssertAndValidate(expected.toString(), actual, fromMap, paths)
  }

  public static void jsonAssertAndValidate(JsonNode expected, JsonNode actual,
      Map<String, Object> fromMap, String... paths) throws IOException {
    // assertJsonAndValidate(expected.toString(), actual, fromMap, paths);
  }
}
