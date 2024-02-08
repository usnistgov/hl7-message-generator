package com.example.messagegenerator.classInjector;

import com.example.messagegenerator.model.Usage;

import java.util.Map;

public class UsageInjector implements ErrorInjector{
    @Override
    public String injectError(String location, Map<String, String> relevantData) {
        String command = "";
        if(relevantData.containsKey("usage")) {
            String usage = relevantData.get("usage");
            if(usage.equals(Usage.R.toString())) {
                command = location + "=\" \" ";
            } else if (usage.equals(Usage.X.toString())) {
                command = location + "=some random value ";
            }
        }
        return command;
    }
}
