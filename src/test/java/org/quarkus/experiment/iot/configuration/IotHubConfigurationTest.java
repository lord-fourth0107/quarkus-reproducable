package org.quarkus.experiment.iot.configuration;

import com.microsoft.azure.sdk.iot.service.ServiceClient;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;


@QuarkusTest
public class IotHubConfigurationTest {
     @Inject
     IotHubConfiguration iot;
     @Inject
     ServiceClient serviceClient;
     @ConfigProperty(name="iothub.device.connection.string")
     String connectionString;

     @Test()
     public void getIotTest() throws URISyntaxException, IOException {
          ServiceClient service = iot.getIot();
          Assertions.assertNotNull(service);
     }

}