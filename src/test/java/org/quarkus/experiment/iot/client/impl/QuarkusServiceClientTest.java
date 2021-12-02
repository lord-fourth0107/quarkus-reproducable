package org.quarkus.experiment.iot.client.impl;

import ca.uhn.hl7v2.HL7Exception;
import com.microsoft.azure.sdk.iot.service.Message;
import com.microsoft.azure.sdk.iot.service.ServiceClient;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.quarkus.experiment.iot.configuration.IotHubConfiguration;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;


@QuarkusTest
public class QuarkusServiceClientTest {

    @Inject
    QuarkusServiceClient client;
    @Inject
    ServiceClient serviceClient;

    @Test
    public void sendMessageToIotHubTest() throws IOException, IotHubException, URISyntaxException, HL7Exception {
        //client.sendMessageToIotHub("How are you");
        //Mockito.doNothing().when(clientMock).send(Mockito.anyString(),Mockito.any(Message.class));
//        QuarkusMock.installMockForType(iot, IotHubConfiguration.class);
//        ServiceClient service = Mockito.mock(ServiceClient.class);
//        Mockito.when(iot.getIot()).thenReturn(service);
        IotHubConfiguration mock = Mockito.mock(IotHubConfiguration.class);
        QuarkusMock.installMockForType(mock, IotHubConfiguration.class);
        serviceClient = Mockito.mock(ServiceClient.class);
        Mockito.when(mock.getIot()).thenReturn(serviceClient);
        client.sendMessageToIotHub("data");

     Mockito.verify(serviceClient).send(Mockito.anyString(), Mockito.any(Message.class));
    }
}