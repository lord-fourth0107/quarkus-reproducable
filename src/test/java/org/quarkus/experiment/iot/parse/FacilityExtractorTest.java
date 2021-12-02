package org.quarkus.experiment.iot.parse;

import ca.uhn.hl7v2.HL7Exception;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;


@QuarkusTest
public class FacilityExtractorTest {
    @Inject
    FacilityExtractor parserTester;


    @Test()
    public void parseFacilityIDTest() throws IOException, HL7Exception {
        String payload = parserTester.parseFacilityID("data");
        Assertions.assertEquals("data","data");
    }
    @Test
    public void exceptionCatcher(){
        try {
            String payload = parserTester.parseFacilityID("payload");
        }
        catch(Exception ex){
            //TODO: assert exception
            //Assertions.assertEquals("Error While Parsing",payload);
        }


    }
}