package com.sgs.vision.event.consumer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.bson.json.JsonParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.sgs.vision.data.interfaces.DeviceDetails;
import com.sgs.vision.data.interfaces.InventorySkuHistoryDetails;
import com.sgs.vision.data.interfaces.IssueHistoryDetails;
import com.sgs.vision.data.interfaces.ItemHistoryDetails;
import com.sgs.vision.data.interfaces.LocationDetails;
import com.sgs.vision.data.interfaces.ReadPointDetails;
import com.sgs.vision.data.interfaces.ZoneDetails;
import com.sgs.vision.data.model.Device;
import com.sgs.vision.data.model.InventorySkuHistory;
import com.sgs.vision.data.model.IssueHistory;
import com.sgs.vision.data.model.ItemHistory;
import com.sgs.vision.data.model.Location;
import com.sgs.vision.data.model.ReadPoint;
import com.sgs.vision.data.model.Zone;
import com.sgs.vision.event.consumer.mappers.CustomObjectMapper;

public class Listener {

  @Autowired
  private CustomObjectMapper customObjectMapper;

  @Autowired
  private ItemHistoryDetails itemHistoryDetails;

  @Autowired
  private IssueHistoryDetails issueHistoryDetails;

  @Autowired
  private DeviceDetails deviceDetails;
  @Autowired
  private LocationDetails locationDetails;

  @Autowired
  private ReadPointDetails readPointDetails;

  @Autowired
  private ZoneDetails zoneDetails;

  @Autowired
  private InventorySkuHistoryDetails inventorySkuHistoryDetails;

  private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.syncEvents}")
  public void receive(String message) throws IOException {
    try {
      JSONObject jsonObj = new JSONObject(message);

      String className = jsonObj.getString("_class");

      String objectType = className.substring(className.lastIndexOf('.') + 1, className.length());
      switch (objectType) {
        case "ItemHistory":
          ItemHistory itemHistory = customObjectMapper.readValue(message, ItemHistory.class);
          logger.info("received message='{}'", itemHistory.getId());
          latch.countDown();
          itemHistoryDetails.insertItemHistory(itemHistory);
          break;
        case "IssueHistory":
          IssueHistory issueHistory = customObjectMapper.readValue(message, IssueHistory.class);
          logger.info("received message='{}'", issueHistory.getId());
          latch.countDown();
          issueHistoryDetails.insertIssueHistory(issueHistory);
          break;
        case "InventorySkuHistory":
          InventorySkuHistory inventorySkuHistory =
              customObjectMapper.readValue(message, InventorySkuHistory.class);
          logger.info("received message='{}'", inventorySkuHistory.getId());
          latch.countDown();
          inventorySkuHistoryDetails.insertInventorySkuHistory(inventorySkuHistory);
          break;
        case "Device":
          Device device = customObjectMapper.readValue(message, Device.class);
          logger.info("received message='{}'", device.getId());
          latch.countDown();
          deviceDetails.insertDevice(device);
          break;
        case "Location":
          Location location = customObjectMapper.readValue(message, Location.class);
          logger.info("received message='{}'", location.getId());
          latch.countDown();
          locationDetails.insertLocation(location);
          break;
        case "ReadPoint":
          ReadPoint readPoint = customObjectMapper.readValue(message, ReadPoint.class);
          logger.info("received message='{}'", readPoint.getId());
          latch.countDown();
          readPointDetails.insertReadPoint(readPoint);
          break;
        case "Zone":
          Zone zone = customObjectMapper.readValue(message, Zone.class);
          logger.info("received message='{}'", zone.getId());
          latch.countDown();
          zoneDetails.insertZone(zone);
          break;

        default:
          logger.error("ObjectType not Supporter by Event Consumer");
      }

    } catch (IOException e) {
      logger.error(e.getLocalizedMessage(), e);
      throw new IOException(e);
    } catch (JSONException e) {
      logger.error(e.getLocalizedMessage(), e);
      throw new JsonParseException(e);
    }

  }



}
