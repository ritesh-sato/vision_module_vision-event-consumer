package com.sgs.vision.event.consumer.test;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventConsumerTest {
	
  @Test
  public void testJsonItemsHistory() throws JSONException {
    String json =
        "{\"name\":\"foo\",\"_class\":\"com.sgs.vision.storage.model.ItemHistory\",\"timestamp\":1475840608763,"
            + "\"payload\":{\"foo\":\"bar\"}}";

    JSONObject jsonObj = new JSONObject(json);
    String className = jsonObj.getString("_class");

    String objectType = className.substring(className.lastIndexOf('.') + 1, className.length());
    switch (objectType) {
      case "ItemHistory":
        Assert.assertEquals(objectType, "ItemHistory");
        break;
      case "IssueHistory":
        Assert.assertEquals(objectType, "ItemHistory");
        break;
      case "InventorySKUHistory":
        Assert.assertEquals(objectType, "ItemHistory");
        break;

      default:
        Assert.assertEquals(objectType, "ItemHistory");
        break;
    }

  }
  
  @Test
  public void testJsonDevices() throws JSONException {
    String json =
        "{\"name\":\"foo\",\"_class\":\"com.sgs.vision.storage.model.Devices\",\"timestamp\":1475840608763,"
            + "\"payload\":{\"foo\":\"bar\"}}";

    JSONObject jsonObj = new JSONObject(json);
    String className = jsonObj.getString("_class");

    String objectType = className.substring(className.lastIndexOf('.') + 1, className.length());
    switch (objectType) {
      case "Devices":
        Assert.assertEquals(objectType, "Devices");
        break;
      case "IssueHistory":
        Assert.assertEquals(objectType, "Devices");
        break;
      case "InventorySKUHistory":
        Assert.assertEquals(objectType, "Devices");
        break;

      default:
        Assert.assertEquals(objectType, "Devices");
        break;
    }

  }
  
  
  @Test
  public void testJsonLocations() throws JSONException {
    String json =
        "{\"name\":\"foo\",\"_class\":\"com.sgs.vision.storage.model.Locations\",\"timestamp\":1475840608763,"
            + "\"payload\":{\"foo\":\"bar\"}}";

    JSONObject jsonObj = new JSONObject(json);
    String className = jsonObj.getString("_class");

    String objectType = className.substring(className.lastIndexOf('.') + 1, className.length());
    switch (objectType) {
      case "Locations":
        Assert.assertEquals(objectType, "Locations");
        break;
      case "IssueHistory":
        Assert.assertEquals(objectType, "Locations");
        break;
      case "InventorySKUHistory":
        Assert.assertEquals(objectType, "Locations");
        break;

      default:
        Assert.assertEquals(objectType, "Locations");
        break;
    }

  }
	
}
