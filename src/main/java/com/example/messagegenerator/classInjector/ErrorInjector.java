package com.example.messagegenerator.classInjector;

import java.util.Map;

public interface ErrorInjector {
public String injectError(String location, Map<String,String> relevantData);
}
