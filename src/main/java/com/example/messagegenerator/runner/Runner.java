package com.example.messagegenerator.runner;

import com.example.messagegenerator.classInjector.*;
import com.example.messagegenerator.messageModifierHelper.MessageModifierHelper;
import com.example.messagegenerator.model.ErrorObject;
import com.example.messagegenerator.model.ErrorType;
import com.example.messagegenerator.model.Usage;
import gov.nist.healthcare.unified.enums.Context;
import gov.nist.healthcare.unified.model.EnhancedReport;
import gov.nist.healthcare.unified.proxy.ValidationProxy;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

public class Runner {

    public static String startingMessage = "MSH|^~\\&|Test EHR Application|X68||NIST Test Iz Reg|20120701082240-0500||VXU^V04^VXU_V04|NIST-IZ-001.00|P|2.5.1|||ER|AL|||||Z22^CDCPHINVS|\r"
            + "PID|1||D26376273^^^NIST MPI^MR||Snow^Madelynn^Ainsley^^^^L|Lam^Morgan^^^^^M|20070706|F||2076-8^Native Hawaiian or Other Pacific Islander^CDCREC|32 Prescott Street Ave^^Warwick^MA^02452^USA^L||^PRN^PH^^^657^5558563|||||||||2186-5^non Hispanic or Latino^CDCREC\r"
            + "PD1|||||||||||02^Reminder/Recall - any method^HL70215|||||A|20120701|20120701\r"
            + "NK1|1|Lam^Morgan^^^^^L|MTH^Mother^HL70063|32 Prescott Street Ave^^Warwick^MA^02452^USA^L|^PRN^PH^^^657^5558563\r"
            + "ORC|RE||IZ-783274^NDA|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1^^^^PRN||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L^^^MD\r"
            + "RXA|0|1|20120814||33332-0010-01^Influenza, seasonal, injectable, preservative free^NDC|0.5|mL^MilliLiter [SI Volume Units]^UCUM||00^New immunization record^NIP001|7832-1^Lemon^Mike^A^^^^^NIST-AA-1^^^^PRN|^^^X68||||Z0860BB|20121104|CSL^CSL Behring^MVX|||CP|A\r"
            + "RXR|C28161^Intramuscular^NCIT|LD^Left Arm^HL70163\r"
            + "OBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V05^VFC eligible - Federally Qualified Health Center Patient (under-insured)^HL70064||||||F|||20120701|||VXC40^Eligibility captured at the immunization level^CDCPHINVS\r"
            + "OBX|2|CE|30956-7^vaccine type^LN|2|88^Influenza, unspecified formulation^CVX||||||F\r"
            + "OBX|3|TS|29768-9^Date vaccine information statement published^LN|2|20120702||||||F\r"
            + "OBX|4|TS|29769-7^Date vaccine information statement presented^LN|2|20120814||||||F\r";

    public void run() throws Exception {
        List<String> messages = generateMessages(10000);
        generateXmlValidationFiles(messages);
    }

    private void updateInjectedErrorsMap(Map<String, Map<ErrorType, Integer>> injectedErrorsMap,
                                         String location, ErrorType errorType) {
        injectedErrorsMap.computeIfAbsent(location, k -> new HashMap<>());
        injectedErrorsMap.get(location).merge(errorType, 1, Integer::sum);
    }

    private ErrorType getRandomErrorType(Map<ErrorType, Map<String, String>> errorTypes) {
        if (errorTypes == null || errorTypes.isEmpty()) {
            // Handle the case when errorTypes is null or empty
            return null; // or throw an exception, or return a default value
        }

        Random random = new Random();
        int index = random.nextInt(errorTypes.size());
        return (ErrorType) errorTypes.keySet().toArray()[index];
    }

//    private String getRandomLocation(Map<String, Map<ErrorType, Map<String, String>>> locationMap) {
//        Random random = new Random();
//        int index = random.nextInt(locationMap.size());
//        return (String) locationMap.keySet().toArray()[index];
//    }

    // Helper method to get relevant data for the error based on the location
    private Map<String, String> getRelevantData(String location) {
        // Implement logic to retrieve relevant data based on the location
        // For example, return a map with dummy data for demonstration purposes
        Map<String, String> data = new HashMap<>();
        data.put("Key1", "Value1");
        data.put("Key2", "Value2");
        return data;
    }

    public int getRandomNumberLessThanInput(int inputNumber) {
        Random random = new Random();
        return random.nextInt(inputNumber);
    }

    public ErrorObject getRandomError(List<ErrorObject> errorList) {
        if (errorList == null || errorList.isEmpty()) {
            // Handle the case when the list is null or empty
            return null; // or throw an exception, or return a default value
        }

        Random random = new Random();
        int index = random.nextInt(errorList.size());
        return errorList.get(index);
    }

    public List<String> generateMessages(int numberOfMessages) {
        UsageInjector usageInjector = new UsageInjector();
        CardinalityInjector cardinalityInjector = new CardinalityInjector();
        LengthInjector lengthInjector = new LengthInjector();
        ValuesetInjector valuesetInjector = new ValuesetInjector();
        Tools tool = new Tools();

        Map<String, ErrorInjector> classes = new HashMap<>();
        classes.put("USAGE", usageInjector);
        classes.put("CARDINALITY", cardinalityInjector);
        classes.put("VALUESET", valuesetInjector);
        classes.put("LENGTH", lengthInjector);

        Map<String, Map<ErrorType, Map<String, String>>> locationMap = new HashMap<>();
        DataFiller dataFiller = new DataFiller();
        dataFiller.populateLocationMap(locationMap);
        Map<String, Map<ErrorType, Integer>> injectedErrorsMap = new HashMap<>();
        List<String> modifiedMessages = new ArrayList<>();
        for (int i = 0; i < numberOfMessages; i++) {
//            Map<String, Map<ErrorType, Map<String, String>>> dynamicLocationMap = new HashMap<>(locationMap);
//            System.out.println(dynamicLocationMap == locationMap); // FALSE
//            System.out.println(dynamicLocationMap.get("A") == locationMap.get("A")); // TRUE
            List<ErrorObject> errorList = tool.flattenLocationMap(locationMap);
            int t = getRandomNumberLessThanInput(locationMap.size() + 1);
            String messageToUse = startingMessage;
            for (int j = 0; j < t; j++) {
                String location = getRandomError(errorList).getLocation();
                ErrorType randomErrorType = getRandomErrorType(locationMap.get(location));

                Map<String, String> relevantData = locationMap.get(location).get(randomErrorType);

                if (randomErrorType != null) {
                    ErrorInjector errorInjector = classes.get(randomErrorType.name());
                    if (errorInjector != null) {
                        errorInjector.injectError(location, relevantData);
                        //Call message modifier and modify message using script
                        MessageModifierHelper messageModifierHelper = new MessageModifierHelper();
                        String modifiedMessage = messageModifierHelper.modifyMessage(messageToUse, errorInjector.injectError(location, relevantData));
                        messageToUse = modifiedMessage;
                        System.out.println("modified Message for eroor" + " j " + ": " + modifiedMessage);


                        updateInjectedErrorsMap(injectedErrorsMap, location, randomErrorType);
                        errorList.remove(randomErrorType);
                        System.out.println("Injected error at location: " + location + " with error type: " + randomErrorType);
                    }
                }
            }
            modifiedMessages.add(messageToUse);
            // add logic to gather modified messages and maybe download as a zip file?

            // add logic to call validation upon modified messages and download XML validation reports
        }


        System.out.println("Injected Errors Map: " + injectedErrorsMap);


        return modifiedMessages;

    }

    public void generateXmlValidationFiles(List<String> modifiedMessages) throws Exception {
        List<String> xmlValidationFiles = new ArrayList<>();
        Tools tools = new Tools();
        int i = 1;
        for (String message : modifiedMessages) {
            ValidationProxy validationProxy = new ValidationProxy("Test", "NIST");

            InputStream profile = Runner.class.getResourceAsStream("/VXU-Z22-Profile.xml");
            InputStream vsLib = Runner.class.getResourceAsStream("/VXU-Z22-Valuesets.xml");
            InputStream constraint = Runner.class.getResourceAsStream("/Constraints1.xml");

            EnhancedReport enhancedReport = validationProxy.validateNew(
                    message,
                    IOUtils.toString(profile, "UTF-8"),
                    vsLib,
                    Arrays.asList(constraint),
                    null,
                    null,
                    null,
                    "aa72383a-7b48-46e5-a74a-82e019591fe7",
                    Context.Free,
                    null
            );
            String xmlReport = (String) enhancedReport.to("xml");

            tools.writeStringToXmlFile("src/main/resources/ValidationReports/ValidationReport" + i + ".xml", xmlReport);
            System.out.println( "Completed writting file " + i + " out of " + modifiedMessages.size() + " files");

            xmlValidationFiles.add(xmlReport);
            i++;
        }
        System.out.println( "Completed writting files");
    }

}