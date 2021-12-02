package org.quarkus.experiment.iot.parse;

import ca.uhn.hl7v2.HL7Exception;

import java.io.IOException;

public interface hl7parser {
    public String parseFacilityID(String HL7data) throws IOException, HL7Exception;
}
