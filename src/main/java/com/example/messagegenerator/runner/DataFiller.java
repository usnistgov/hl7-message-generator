package com.example.messagegenerator.runner;

import com.example.messagegenerator.model.ErrorType;
import com.example.messagegenerator.model.Usage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;



public class DataFiller {

    public void populateLocationMap(Map<String, Map<ErrorType, Map<String, String>>> locationMap) {
        BiConsumer<String, Map<ErrorType, Map<String, String>>> addOrUpdateLocation = (location, data) -> {
            if (locationMap.containsKey(location)) {
                locationMap.get(location).putAll(data);
            } else {
                locationMap.put(location, data);
            }
        };

        // Usage Map
        Map<ErrorType, Map<String, String>> usageMap = new HashMap<>();
        Map<String, String> usageData = new HashMap<>();
        usageData.put("usage", Usage.R.toString());
        usageMap.put(ErrorType.USAGE, usageData);
        addOrUpdateLocation.accept("PID-1", usageMap);
        addOrUpdateLocation.accept("PID-5.1", usageMap);
        addOrUpdateLocation.accept("PID-7.1", usageMap);
        addOrUpdateLocation.accept("PID-8", usageMap);
        addOrUpdateLocation.accept("PID-10", usageMap);
        addOrUpdateLocation.accept("PID-6.1.1", usageMap);
//        addOrUpdateLocation.accept("MSH-1", usageMap);
//        addOrUpdateLocation.accept("MSH-2", usageMap);

        // Cardinality Map
        Map<ErrorType, Map<String, String>> cardinalityMap = new HashMap<>();
        Map<String, String> cardinalityData1 = new HashMap<>();
        cardinalityData1.put("minCardinality", "0");
        cardinalityData1.put("maxCardinality", "1");
        cardinalityMap.put(ErrorType.CARDINALITY, cardinalityData1);
        addOrUpdateLocation.accept("PID-6", cardinalityMap);
        addOrUpdateLocation.accept("PID-7", cardinalityMap);
        addOrUpdateLocation.accept("PID-8", cardinalityMap);
        addOrUpdateLocation.accept("MSH-11", cardinalityMap);
        addOrUpdateLocation.accept("MSH-12", cardinalityMap);

        // Length Map
        Map<ErrorType, Map<String, String>> lengthMap = new HashMap<>();
        Map<String, String> lengthData4 = new HashMap<>();
        lengthData4.put("minLength", "0");
        lengthData4.put("maxLength", "4");
        lengthMap.put(ErrorType.LENGTH, lengthData4);
        addOrUpdateLocation.accept("PID-1", lengthMap);
        Map<String, String> lengthData250 = new HashMap<>();
        lengthData250.put("minLength", "0");
        lengthData250.put("maxLength", "250");
        lengthMap.put(ErrorType.LENGTH, lengthData250);
        addOrUpdateLocation.accept("PID-3", lengthMap);
        addOrUpdateLocation.accept("PID-5", lengthMap);
        addOrUpdateLocation.accept("PID-6", lengthMap);
        addOrUpdateLocation.accept("PID-8", lengthMap);
        addOrUpdateLocation.accept("PID-10", lengthMap);
        addOrUpdateLocation.accept("PID-11", lengthMap);

        // Valueset Map
        Map<ErrorType, Map<String, String>> valuesetMap = new HashMap<>();
        Map<String, String> valuesetData = new HashMap<>();
        valuesetData.put("valueset", "2.16.840.1.113883.12.292");
        valuesetMap.put(ErrorType.VALUESET, valuesetData);
        addOrUpdateLocation.accept("PID-3.4", valuesetMap);
        addOrUpdateLocation.accept("PID-3.5", valuesetMap);
        addOrUpdateLocation.accept("PID-3.4.1", valuesetMap);
        addOrUpdateLocation.accept("PID-8", valuesetMap);
        addOrUpdateLocation.accept("MSH-21", valuesetMap);
        addOrUpdateLocation.accept("MSH-3", valuesetMap);
        addOrUpdateLocation.accept("MSH-4", valuesetMap);
    }
}
