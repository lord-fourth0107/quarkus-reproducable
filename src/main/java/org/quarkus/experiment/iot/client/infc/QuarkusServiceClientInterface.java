package org.quarkus.experiment.iot.client.infc;


import ca.uhn.hl7v2.HL7Exception;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

import java.io.IOException;


public interface QuarkusServiceClientInterface {

    void sendMessageToIotHub(String payload) throws IOException, IotHubException, HL7Exception;
}
