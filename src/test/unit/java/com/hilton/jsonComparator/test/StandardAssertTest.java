/**
 * Mohak
 */
package com.hilton.jsonComparator.test;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.JsonAssert.when;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_ARRAY_ITEMS;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_VALUES;
import static net.javacrumbs.jsonunit.core.Option.TREATING_NULL_AS_ABSENT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hilton.jsonComparator.ValidateAndCompareUtil;
import com.hilton.jsonComparator.test.config.StandardTestConfig;

/**
 * Reference
 * 
 * @link https://github.com/lukas-krecan/JsonUnit#standard
 */

public class StandardAssertTest extends StandardTestConfig {

  /**
   * Test 1
   */
  @Test
  public void compareJSON() {
    // Takes in Object as argument. Good to compare JSON or strings.
    // Happy cases
    assertJsonEquals("{\"test\":1}", "{\"test\": 1}");
    assertJsonEquals(test1Expected, test1Actual1);

    // Ahhh! Problem
    // assertJsonEquals(test1Expected, test1Actual2);

    // What if a node gets missing ? Hmm. Lets see.
    // Extra: if actual has extra node.
    // Missing: if actual has missing node.
  }

  /**
   * Test 2; description : ignore paths from json
   */
  @Test
  public void compareJSONWithIgnoredPaths() {
    assertJsonEquals("{\"test\":\"${json-unit.ignore}\"}",
        "{\"test\": {\"object\" : {\"another\" : 1}}}");

    assertJsonEquals(test2Expected, test2Actual1);
  }

  /**
   * Test 3; description : use of regex in paths
   */
  @Test
  public void compareJSONWithRegex() {
    assertJsonEquals("{\"test\": \"${json-unit.regex}[A-Z]+\"}", "{\"test\": \"ABCD\"}");

    assertJsonEquals(test3Expected, test3Actual1);
  }

  /**
   * Test 4; description: With PlaceHolders
   */
  @Test
  public void compareJSONWithPlaceHolders() {
    assertJsonEquals("{\"test\":\"${json-unit.any-string}\"}", "{\"test\":\"value\"}");

    assertJsonEquals("{\"test\":\"${json-unit.any-boolean}\"}", "{\"test\":true}");

    assertJsonEquals("{\"test\":\"${json-unit.any-number}\"}", "{\"test\":1.1}");

    // Uncomment me :-(

    assertJsonEquals(test4Expected, test4Actual1);
  }

  /**
   * Test 5; description: With Options
   */
  @Test
  public void compareJSONWithOptions() {

    // Accepts 3 parameters - Expected, Actual and Configuration

    // TREATING NULL AS ABSENT
    assertJsonEquals("{\"test\":{\"a\":1}}", "{\"test\":{\"a\":1, \"b\": null, \"c\": null}}",
        when(TREATING_NULL_AS_ABSENT));

    // IGNORING_ARRAY_ORDER
    assertJsonEquals("{\"test\":[1,2,3]}", "{\"test\":[3,2,1]}", when(IGNORING_ARRAY_ORDER));

    // IGNORING_ARRAY_ORDER, IGNORING_EXTRA_ARRAY_ITEMS
    assertJsonEquals("{\"test\":[1,2,3]}", "{\"test\":[5,5,4,4,3,3,2,2,1,1]}",
        when(IGNORING_EXTRA_ARRAY_ITEMS, IGNORING_ARRAY_ORDER));

    // IGNORING_VALUES - ignores values and compares only types [STRUCTURE]
    assertJsonEquals("{\"test\":{\"a\":1,\"b\":2,\"c\":3}}", "{\"test\":{\"a\":3,\"b\":2,\"c\":1}}",
        when(IGNORING_VALUES));
  }

  /**
   * Test 6; description: Custom Methods
   * 
   * @throws IOException
   */
  @Test
  public void compareJSONWithCustomMethods() throws IOException {

    // Accepts 3 paramters - Expected, Actual and pathsToIgnore
    String[] pathToIgnore =
        { "widget.text.data", "widget.text.size", "widget.text.style", "widget.text.name" };
    ValidateAndCompareUtil.assertJSONEquals(test6Expected, test6Actual1, pathToIgnore);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("data", "sampleJsonData");
    map.put("name", "Test 6 Data");
    map.put("any key", "any value");

    String[] pathToIgnore1 = { "widget.text.size", "widget.text.style" };

    // Accepts 3 paramters - Expected, Actual and pathsToIgnore
    ValidateAndCompareUtil.assertJsonAndValidate(test6Expected, test6Actual1, map, pathToIgnore1);

  }
}
