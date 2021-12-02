package org.quarkus.experiment.iot.kafka;

import ca.uhn.hl7v2.HL7Exception;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quarkus.experiment.iot.client.impl.QuarkusServiceClient;

import javax.inject.Inject;
import java.io.IOException;

@QuarkusTest
public class QuarkusKafkaReactiveConsumerTest {

    @InjectMock
    QuarkusServiceClient quarkusServiceClient;

    @Inject
    QuarkusKafkaReactiveConsumer quarkusKafkaReactiveConsumer;


    @Test
    public void positiveWorkflow() throws IOException, IotHubException, HL7Exception {
        Mockito.doNothing().when(quarkusServiceClient).sendMessageToIotHub(Mockito.anyString());
        quarkusKafkaReactiveConsumer.initMetrics();
        ConsumerRecord<String,String> consumerRecord = new ConsumerRecord<String,String>("abc",0,0,"abc","{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}");
        Metadata metadata = Metadata.of(new IncomingKafkaRecordMetadata<>(consumerRecord));
        Message<String> message = Message.of("{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}",metadata);
        quarkusKafkaReactiveConsumer.process(message);

    }



    @Test
    public void exceptionWorkflow() throws IOException, IotHubException, HL7Exception {
        Mockito.doThrow(new IOException()).when(quarkusServiceClient).sendMessageToIotHub(Mockito.anyString());
        quarkusKafkaReactiveConsumer.initMetrics();
        ConsumerRecord<String,String> consumerRecord = new ConsumerRecord<String,String>("abc",0,0,"abc","{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}");
        Metadata metadata = Metadata.of(new IncomingKafkaRecordMetadata<>(consumerRecord));
        Message<String> message = Message.of("{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}",metadata);
        try{
            quarkusKafkaReactiveConsumer.process(message);
        }
        catch (Exception exception)
        {
            System.out.println("Exception thrown");
        }



    }
    @Test
    public void iotExceptionWorkflow() throws IOException, IotHubException, HL7Exception {
        Mockito.doThrow(new IotHubException()).when(quarkusServiceClient).sendMessageToIotHub(Mockito.anyString());
        quarkusKafkaReactiveConsumer.initMetrics();

        ConsumerRecord<String,String> consumerRecord = new ConsumerRecord<String,String>("abc",0,0,"abc","{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}");
        Metadata metadata = Metadata.of(new IncomingKafkaRecordMetadata<>(consumerRecord));
        Message<String> message = Message.of("{\n" +
                "  \"resourceType\": \"Observation\",\n" +
                "  \"id\": \"example-genetics-1\",\n" +
                "  \"text\": {\n" +
                "    \"status\": \"generated\",\n" +
                "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: example-genetics-1</p><p><b>status</b>: final</p><p><b>code</b>: Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result. <span>(Details : {LOINC code '55233-1' = 'Genetic analysis master panel - Blood or Tissue by Molecular genetics method', given as 'Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.'})</span></p><p><b>subject</b>: <a>Molecular Lab Patient ID: HOSP-23456</a></p><p><b>issued</b>: 03/04/2013 3:30:10 PM</p><p><b>performer</b>: <a>Molecular Diagnostics Laboratory</a></p><p><b>value</b>: Positive <span>(Details : {SNOMED CT code '10828004' = 'Positive', given as 'Positive'})</span></p><p><b>specimen</b>: <a>Molecular Specimen ID: MLD45-Z4-1234</a></p></div>\"\n" +
                "  },\n" +
                "  \"extension\": [\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGene\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://www.genenames.org\",\n" +
                "            \"code\": \"3236\",\n" +
                "            \"display\": \"EGFR\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsDNARegionName\",\n" +
                "      \"valueString\": \"Exon 21\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"http://hl7.org/fhir/StructureDefinition/observation-geneticsGenomicSourceClass\",\n" +
                "      \"valueCodeableConcept\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"http://loinc.org\",\n" +
                "            \"code\": \"LA6684-0\",\n" +
                "            \"display\": \"somatic\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"final\",\n" +
                "  \"code\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://loinc.org\",\n" +
                "        \"code\": \"55233-1\",\n" +
                "        \"display\": \"Genetic analysis master panel-- This is the parent OBR for the panel holding all of the associated observations that can be reported with a molecular genetics analysis result.\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"subject\": {\n" +
                "    \"reference\": \"Patient/example\",\n" +
                "    \"display\": \"Molecular Lab Patient ID: HOSP-23456\"\n" +
                "  },\n" +
                "  \"issued\": \"2013-04-03T15:30:10+01:00\",\n" +
                "  \"performer\": [\n" +
                "    {\n" +
                "      \"reference\": \"Practitioner/example\",\n" +
                "      \"display\": \"Molecular Diagnostics Laboratory\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"valueCodeableConcept\": {\n" +
                "    \"coding\": [\n" +
                "      {\n" +
                "        \"system\": \"http://snomed.info/sct\",\n" +
                "        \"code\": \"10828004\",\n" +
                "        \"display\": \"Positive\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"specimen\": {\n" +
                "    \"reference\": \"Specimen/genetics-example1-somatic\",\n" +
                "    \"display\": \"Molecular Specimen ID: MLD45-Z4-1234\"\n" +
                "  }\n" +
                "}",metadata);
        try{
            quarkusKafkaReactiveConsumer.process(message);
        }
        catch (Exception exception)
        {
            System.out.println("Exception thrown");
        }



    }



}
