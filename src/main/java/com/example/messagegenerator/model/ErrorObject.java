package com.example.messagegenerator.model;

import java.util.Map;

public class ErrorObject {
    private String location;
    private String errorType;
    private Map<String, String> relevantData;

    public ErrorObject() {
        // Default constructor
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Map<String, String> getRelevantData() {
        return relevantData;
    }

    public void setRelevantData(Map<String, String> relevantData) {
        this.relevantData = relevantData;
    }
}
