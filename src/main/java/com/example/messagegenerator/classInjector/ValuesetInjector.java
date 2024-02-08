package com.example.messagegenerator.classInjector;

import java.util.Map;

public class ValuesetInjector implements ErrorInjector{
    @Override
    public String injectError(String location, Map<String, String> relevantData) {
        return  location + "=NotAValueInValueset";

    }
}
