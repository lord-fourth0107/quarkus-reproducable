package org.quarkus.experiment.iot.client.impl;

import ca.uhn.hl7v2.HL7Exception;
import com.microsoft.azure.sdk.iot.service.Message;
import com.microsoft.azure.sdk.iot.service.ServiceClient;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.quarkus.experiment.iot.client.infc.QuarkusServiceClientInterface;
import org.quarkus.experiment.iot.message.builder.infc.IotHubMessageBuilderInterface;
import org.quarkus.experiment.iot.parse.FacilityExtractor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;


@ApplicationScoped
public class QuarkusServiceClient implements QuarkusServiceClientInterface {

    private static final Logger LOG = Logger.getLogger(QuarkusServiceClient.class);

    @Inject
    IotHubMessageBuilderInterface iotHubMessageBuilderInterface;

    @Inject
    ServiceClient serviceClient;

    @Inject
    FacilityExtractor parser;



    @ConfigProperty(name="iot.device.id")
    String deviceId;


    public void sendMessageToIotHub(String message) throws IOException, IotHubException, HL7Exception {
        Message iotHubMessage = iotHubMessageBuilderInterface.generateIotHubMessage(message);
        String facilityId = parser.parseFacilityID(message);
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("facility_id", facilityId);
        String time = String.valueOf(Instant.now().toEpochMilli());
        map.put("sending_time",time);
        iotHubMessage.setProperties(map);
        serviceClient.send(deviceId,iotHubMessage);
  }



}
