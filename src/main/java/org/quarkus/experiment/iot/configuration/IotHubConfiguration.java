package org.quarkus.experiment.iot.configuration;



import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.ServiceClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;


@ApplicationScoped
public class IotHubConfiguration {

    @ConfigProperty(name="iothub.device.connection.string")
    String connectionString;
    @ConfigProperty(name="iothub.host.name")
    String hostname;


    private static final Logger LOG = Logger.getLogger(IotHubConfiguration.class);


    @Produces
    public ServiceClient getIot() throws IOException {
        LOG.info("Inside Service Client1 Bean");
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId("clientid")
                .clientSecret("secret")
                .tenantId("tenant")
                .build();
        ServiceClient serviceClient = new ServiceClient(hostname,clientSecretCredential,IotHubServiceClientProtocol.AMQPS);
        serviceClient.open();
        return serviceClient;
    }


}


