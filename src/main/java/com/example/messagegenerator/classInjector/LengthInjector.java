package com.example.messagegenerator.classInjector;

import com.example.messagegenerator.runner.Tools;

import java.util.Map;

public class LengthInjector implements ErrorInjector {

    @Override
    public String injectError(String location, Map<String, String> relevantData) {
        String command = "";
        Tools tools = new Tools();
        if (relevantData.containsKey("minLength") && relevantData.containsKey("maxLength")) {
            String maxLength = relevantData.get("maxLength");
            String minLength = relevantData.get("minLength");
            command = location + "=" + tools.generateString(Integer.parseInt(maxLength) + 1);
        }
        return command;

    }
}
