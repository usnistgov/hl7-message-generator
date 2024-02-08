package com.example.messagegenerator.model;

public class Length {
    private String minLength;
    private String maxLength;

    public Length(String minLength, String maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
}
