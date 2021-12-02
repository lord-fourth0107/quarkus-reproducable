package org.quarkus.experiment.iot.message.builder.impl;

import com.microsoft.azure.sdk.iot.service.Message;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;

@QuarkusTest
public class IotHubMessageBuilderTest {
    @Inject
    IotHubMessageBuilder messageBuilderTester;
    @Test
    public void generateIotHubMessageTest() throws UnsupportedEncodingException {
        Message message =messageBuilderTester.generateIotHubMessage("message passed");
        Assertions.assertNotNull(message);
    }
}