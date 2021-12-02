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
        client.sendMessageToIotHub("MSH|^~\\&|GE_Mural_DH_NM|GE_BLR_LAB|ASCOM|GE_MKE_ILAB|20210122052031.040+0000||ORU^R40^ORU_R40|75063efd-903a-4033-a70c-6c6488e93859|P|2.6|||AL|NE||UNICODE UTF-8|||IHE_PCD_ACM_001^IHE PCD^1.3.6.1.4.1.19376.1.6.1.4.1^ISO\n" +
                "PID|||PID-12345||Robert^Julie^|\n" +
                "PV1||I|ICU^B1^B1^GE_BLR_LAB\n" +
                "OBR|1|56eb716f-ce7a-3b85-909c-a09d31fe324f^GE_Mural_DH_NM^ICU&B1&B1&GE_BLR_LAB|56eb716f-ce7a-3b85-909c-a09d31fe324f^GE_Mural_DH_NM^ICU&B1&B1&GE_BLR_LAB|196616^MDC_EVT_ALARM^MDC|||20210120082326.275+0000||||||||||||||||||||||56eb716f-ce7a-3b85-909c-a09d31fe324f^56eb716f-ce7a-3b85-909c-a09d31fe324f\n" +
                "OBX|1||69965^MDC_DEV_MON_PHYSIO_MULTI_PARAM_MDS^MDC|1.0.0.0|||||||X|||||||\n" +
                "OBX|2|ST|0^MDCX_EVT_ALERT_ADVISORY^MDC|1.0.0.0.1|HYPERTENSION-Alert Recheck BP|||A~PM~SA|||R|||20210120082326.275+0000||||GE_Mural_DH_NM\n" +
                "OBX|3|ST|68164^MDC_ATTR_ALERT_SOURCE^MDC|1.0.0.0.2|GE_Mural_DH_NM|||A~PM~SA|||R\n" +
                "OBX|4|ST|68165^MDC_ATTR_EVENT_PHASE^MDC|1.0.0.0.3|update|||A~PM~SA|||R\n" +
                "OBX|5|ST|68166^MDC_ATTR_ALERT_STATE^MDC|1.0.0.0.4|active|||A~PM~SA|||R\n" +
                "OBX|6|ST|68167^MDC_ATTR_ALARM_INACTIVATION_STATE^MDC|1.0.0.0.5|enabled|||A~PM~SA|||R\n" +
                "OBX|7|ST|68484^MDC_ATTR_ALARM_PRIORITY^MDC|1.0.0.0.6|PM|||A~PM~SA|||R\n" +
                "OBX|8|ST|68485^MDC_ATTR_ALERT_TYPE^MDC|1.0.0.0.7|SA|||A~PM~SA|||R\n");

     Mockito.verify(serviceClient).send(Mockito.anyString(), Mockito.any(Message.class));
    }
}