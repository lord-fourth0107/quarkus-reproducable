package org.quarkus.experiment.iot.parse;

import ca.uhn.hl7v2.HL7Exception;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FacilityExtractor implements hl7parser {

    private final Logger log = Logger.getLogger(String.valueOf(FacilityExtractor.class));
    public String parseFacilityID(String HL7data) throws HL7Exception {

        log.info("Ready to extract Facility");

            return "facility";





    }

}
