package com.example.messagegenerator.classInjector;

import com.example.messagegenerator.model.Usage;

import java.util.Map;

public class CardinalityInjector implements  ErrorInjector{
    @Override
    public String injectError(String location, Map<String, String> relevantData) {
        // PID-5.1#2=Smith
        String command = "";
        if(relevantData.containsKey("minCardinality") && relevantData.containsKey("maxCardinality")) {
            String minCardinality = relevantData.get("minCardinality");
            String maxCardinality = relevantData.get("maxCardinality");
            command = location + "#" + String.valueOf(Integer.parseInt(maxCardinality)+1) + "=maxCardinality+1";
        }
        return command;
    }
}
